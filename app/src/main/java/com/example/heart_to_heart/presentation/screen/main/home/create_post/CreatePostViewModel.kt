package com.example.heart_to_heart.presentation.screen.main.home.create_post

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.CreatePostUseCase
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostError
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import com.example.heart_to_heart.presentation.base.BaseViewModel

class CreatePostViewModel
constructor(
    private val createPostUseCase: CreatePostUseCase
) : BaseViewModel() {

    val routingEvent: MutableLiveData<CreatePostVMRoutingEvent?> = MutableLiveData(null)

    val imageUris: MutableList<Uri> = mutableListOf()
    val imageUrisLiveData: MutableLiveData<MutableList<Uri>> = MutableLiveData(imageUris)
    val contentLiveData: MutableLiveData<String> = MutableLiveData("")

    fun post() {
        createPostUseCase.content = contentLiveData.value!!
        createPostUseCase.imageUris = imageUrisLiveData.value!!
        createPostUseCase.execute().subscribe { result ->
            when(result) {
                is CreatePostResult.SUCCESS -> {
                    routingEvent.value = CreatePostVMRoutingEvent.CLOSE
                }
                is CreatePostResult.FAILURE -> {
                    when (result.error) {
                        CreatePostError.UNKNOWN_ERROR -> {
                            routingEvent.value = CreatePostVMRoutingEvent.UNKNOWN_ERROR
                        }
                        else -> {
                            routingEvent.value = CreatePostVMRoutingEvent.UNKNOWN_ERROR
                        }
                    }
                }
            }
        }.apply { disposables.add(this) }
    }
}