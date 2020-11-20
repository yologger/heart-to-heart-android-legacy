package com.example.heart_to_heart.presentation.screen.authorization.signup

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object SignUpFragmentBindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}