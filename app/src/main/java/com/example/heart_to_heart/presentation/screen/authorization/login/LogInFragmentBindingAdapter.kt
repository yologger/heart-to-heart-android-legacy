package com.example.heart_to_heart.presentation.screen.authorization.login

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object LogInFragmentBindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}