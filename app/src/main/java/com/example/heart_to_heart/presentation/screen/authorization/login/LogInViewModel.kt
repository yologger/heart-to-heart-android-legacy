package com.example.heart_to_heart.presentation.screen.authorization.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.LogInUseCase
import com.example.heart_to_heart.infrastructure.model.LogInError
import com.example.heart_to_heart.infrastructure.model.LogInResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class LogInViewModel
constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }
    val didRoutingChange = PublishSubject.create<LogInFragmentRoutingOptions>()

    fun logIn(email: String, password: String) {
        logInUseCase.email = email
        logInUseCase.password = password

        logInUseCase.execute().subscribe({ logInResult ->
            when(logInResult) {
                is LogInResult.SUCCESS -> {
                    didRoutingChange.onNext(LogInFragmentRoutingOptions.SHOW_MAIN)
                }
                is LogInResult.FAILURE -> {
                    when(logInResult.error) {
                        LogInError.NETWORK_CONNECTION_ERROR -> {
                            didRoutingChange.onNext(LogInFragmentRoutingOptions.NETWORK_CONNECTION_ERROR)
                        }
                        LogInError.INVALID_EMAIL -> {
                            didRoutingChange.onNext(LogInFragmentRoutingOptions.INVALID_EMAIL)
                        }
                        LogInError.INVALID_PASSWORD -> {
                            didRoutingChange.onNext(LogInFragmentRoutingOptions.INVALID_PASSWORD)
                        }
                    }
                }
            }
        }, {
            Log.d("YOLO", "ERROR")
        }, {
            Log.d("YOLO", "COMPLETED.")
        }, {
            Log.d("YOLO", "DISPOED.")
        }).apply { disposables.add(this) }
    }
}