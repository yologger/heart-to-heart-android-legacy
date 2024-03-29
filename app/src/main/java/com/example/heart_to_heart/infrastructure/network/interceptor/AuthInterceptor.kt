package com.example.heart_to_heart.infrastructure.network.interceptor

import android.util.Log
import com.example.heart_to_heart.application.Constants.Companion.BASE_URL
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.RefreshTokensResponse
import com.google.gson.Gson
import okhttp3.*
import java.net.HttpURLConnection

const val REFRESH_TOKEN_URL = "${BASE_URL}/auth/token"

class AuthInterceptor
constructor(
    private val sessionStorage: SessionStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var accessToken = sessionStorage.getAccessToken()
        if (accessToken == null) {
            val jsonString = """{"code":-10,"error_message":"No access token"}"""
            val responseBody = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString)

            return Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .message("No access token")
                .code(400)
                .body(responseBody)
                .build()
        } else {
            var response = chain.proceed(requestWithAccessToken(chain.request(), accessToken))
            if (response.isSuccessful) {
                return response
            } else {
                var refreshToken = sessionStorage.getRefreshToken()!!
                var refreshTokenResponse = refreshTokens(refreshToken)
                return if (refreshTokenResponse.isSuccessful) {
                    response?.close()
                    refreshTokenResponse?.close()
                    var newAccessToken = sessionStorage.getAccessToken()!!

                    // Retry
                    var retryResponse =
                        chain.proceed(requestWithAccessToken(chain.request(), newAccessToken))
                    retryResponse
                } else {
                    response?.close()
                    refreshTokenResponse
                }
            }
        }
    }

    private fun requestWithAccessToken(request: Request, accessToken: String): Request {
        return request
            .newBuilder()
            .header("Authorization", "Bearer ${accessToken}")
            .build()
    }

    private fun refreshTokens(refreshToken: String): Response {

        var okHttpClient = OkHttpClient()
        var requestFormBody = FormBody.Builder().add("refresh_token", refreshToken).build()
        var request = Request.Builder().url(REFRESH_TOKEN_URL).post(requestFormBody).build()
        var response = okHttpClient.newCall(request).execute()

        // Refreshing tokens succeed.
        if (response.isSuccessful) {
            val body = response?.body()?.string()!!
            val gson = Gson()
            var refreshTokensResponse =
                gson.fromJson<RefreshTokensResponse>(body, RefreshTokensResponse::class.java)
            val tokens = Tokens(
                refreshTokensResponse.data.accessToken,
                refreshTokensResponse.data.refreshToken
            )
            sessionStorage.updateTokens(tokens)

        } else {
            if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // Automatically Log out
                // Go to response.isSuccessful == false in onResponse()
                sessionStorage.removeSession()
            }
        }
        return response
    }
}