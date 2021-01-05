package com.example.heart_to_heart.presentation.screen.main.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.GetUserInfoUseCase
import com.example.heart_to_heart.domain.usecase.LogOutUseCase
import com.example.heart_to_heart.domain.usecase.UploadAvatarImageUseCase
import com.example.heart_to_heart.infrastructure.network.user_api.model.UploadAvatarImageResult
import com.example.heart_to_heart.presentation.base.BaseViewModel
import java.net.URI

class ProfileViewModel
constructor(
    private val uploadAvatarImageUseCase: UploadAvatarImageUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logOutUseCase: LogOutUseCase
) : BaseViewModel() {

    val routingEvent: MutableLiveData<ProfileVMRoutingEvent?> = MutableLiveData(null)
    val avatarImageLiveData: MutableLiveData<String?> = MutableLiveData(null)


    fun uploadAvatarImage(uri: Uri) {
        uploadAvatarImageUseCase.imageUri = uri
        uploadAvatarImageUseCase.execute().subscribe({ result ->
            when(result) {
                is UploadAvatarImageResult.SUCCESS -> {
                    avatarImageLiveData.setValue(result.data.uri)
                }
                is UploadAvatarImageResult.FAILURE -> {

                }
            }
        }, {

        }, {

        }, {

        }).apply { disposables.add(this) }
    }

    fun logOut() {
        logOutUseCase.execute().subscribe({
            if(it) {
                routingEvent.setValue(ProfileVMRoutingEvent.SHOW_LOGIN)
                routingEvent.setValue(null)
            } else {
                routingEvent.setValue(ProfileVMRoutingEvent.SHOW_LOGIN)
                routingEvent.setValue(null)
            }
        }, {

        }, {

        }).apply { disposables.add(this) }
    }
}