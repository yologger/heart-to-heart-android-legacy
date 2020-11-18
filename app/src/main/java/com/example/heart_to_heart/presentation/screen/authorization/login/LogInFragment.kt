package com.example.heart_to_heart.presentation.screen.authorization.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentLogInBinding
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class LogInFragment : BaseFragment() {

    private val viewModel: LogInViewModel by viewModel()
    private lateinit var binding: FragmentLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initUI()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.routingEvent.observe(this.viewLifecycleOwner, Observer { event ->
            when(event) {
                LogInVMRoutingEvent.SHOW_SIGN_UP -> {
                    viewModel.routingEvent.value = null
                    router.showSignUp()
                }
                LogInVMRoutingEvent.SHOW_FIND_PASSWORD -> {}
                null -> { }
            }
        })
    }

    private fun initUI() {

    }
}

object LogInFragmentBindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
//
//    @BindingAdapter("app:emailValidator")
//    @JvmStatic
//    fun validateEmail(textInputLayout: TextInputLayout, email: String?) {
//        var emailValue = email?.trim()
//        Log.d("YOLO", "emailValue: ${emailValue!!}")
//        if (emailValue == null) {
//            textInputLayout.error = "Field can't be empty"
//        } else {
//            if(emailValue.isEmpty()) {
//                textInputLayout.error = "Field can't be empty"
//            } else {
//                textInputLayout.error = null
//            }
//        }
//    }
}


//package com.example.heart_to_heart.presentation.screen.authorization.login
//
//import android.os.Bundle
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import com.example.heart_to_heart.R
//import com.example.heart_to_heart.presentation.base.BaseFragment
//import kotlinx.android.synthetic.main.fragment_log_in.*
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class LogInFragment : BaseFragment() {
//
//    private val viewModel: LogInViewModel by viewModel()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_log_in, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        this.initBinding()
//        this.initUI()
//    }
//
//    private fun initBinding() {
//        this.viewModel.didRoutingChange.subscribe { option ->
//            when(option) {
//                LogInFragmentRoutingOption.SHOW_MAIN -> {
//                    router.showMain()
//                }
//                LogInFragmentRoutingOption.INVALID_EMAIL -> {
//                    var toast = Toast.makeText(activity, "INVALID EMAIL", Toast.LENGTH_LONG)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
//                }
//                LogInFragmentRoutingOption.INVALID_PASSWORD -> {
//                    var toast = Toast.makeText(activity, "INVALID PASSWORD", Toast.LENGTH_LONG)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
//                }
//                LogInFragmentRoutingOption.NETWORK_CONNECTION_ERROR -> {
//                    var toast = Toast.makeText(activity, "NETWORK CONNECTION ERROR", Toast.LENGTH_LONG)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
//                }
//            }
//
//        }.apply { disposables.add(this) }
//    }
//
//    private fun initUI() {
//        fragment_log_in_til_email.editText?.setText("benzema@gmail.com")
//        fragment_log_in_til_password.editText?.setText("12345")
//        fragment_log_in_btn_login.setOnClickListener {
//            // this.viewModel.test()
//            // router.showMain()
//            // confirmInput()
//            logIn()
//        }
//
//        fragment_log_in_btn_signup.setOnClickListener { router.showSignUp() }
//    }
//
//    private fun validateEmail(): Boolean {
//        var emailValue = fragment_log_in_til_email.editText?.text.toString().trim()
//
//        if (emailValue.isEmpty()) {
//            fragment_log_in_til_email.error = "Field can't be empty"
//            return false
//        } else {
//            fragment_log_in_til_email.error = null
//            return true
//        }
//    }
//
//    private fun validatePassword(): Boolean {
//        var passwordValue = fragment_log_in_til_password.editText?.text.toString().trim()
//        if (passwordValue.isEmpty()) {
//            fragment_log_in_til_password.error = "Field can't be empty"
//            return false
//        } else {
//            fragment_log_in_til_password.error = null
//            return true
//        }
//    }
//
//    fun confirmInput() {
//        if (!validateEmail() or !validatePassword()) {
//            return
//        }
//    }
//
//    private fun logIn() {
//        val email = fragment_log_in_til_email.editText?.text.toString().trim()
//        val password = fragment_log_in_til_password.editText?.text.toString().trim()
//        this.viewModel.logIn(email, password)
//    }
//
//}


