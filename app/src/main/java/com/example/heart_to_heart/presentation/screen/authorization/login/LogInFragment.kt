package com.example.heart_to_heart.presentation.screen.authorization.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : BaseFragment() {

    private val viewModel: LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initBinding()
        this.initUI()
    }

    private fun initBinding() {
        // this.viewModel.test()
    }

    private fun initUI() {
        fragment_log_in_btn_login.setOnClickListener {
            // this.viewModel.test()
            // router.showMain()
            confirmInput()
        }

        fragment_log_in_btn_signup.setOnClickListener { router.showSignUp() }
    }

    private fun validateEmail(): Boolean {
        var emailValue = fragment_log_in_til_email.editText?.text.toString().trim()

        if (emailValue.isEmpty()) {
            fragment_log_in_til_email.error = "Field can't be empty"
            return false
        } else {
            fragment_log_in_til_email.error = null
            return true
        }
    }

    private fun validatePassword(): Boolean {
        var passwordValue = fragment_log_in_til_password.editText?.text.toString().trim()
        if (passwordValue.isEmpty()) {
            fragment_log_in_til_password.error = "Field can't be empty"
            return false
        } else {
            fragment_log_in_til_password.error = null
            return true
        }
    }

    fun confirmInput() {
        // if (!validateEmail() || !validateFirstname() || !validateLastname() || !validateNickname() || !validatePassword()) {
        if (!validateEmail() or !validatePassword()) {
            return
        }

        Toast.makeText(activity, fragment_log_in_til_email.editText?.text.toString(), Toast.LENGTH_SHORT).show()
    }

}