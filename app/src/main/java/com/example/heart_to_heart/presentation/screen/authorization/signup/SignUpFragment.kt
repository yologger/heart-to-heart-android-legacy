package com.example.heart_to_heart.presentation.screen.authorization.signup

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentSignUpBinding
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment() {

    private val viewModel: SignUpViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initBinding()
        this.initUI()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.routingEvent.observe(this.viewLifecycleOwner, Observer { event ->
            when(event) {
                null -> { }
                SignUpVMRoutingEvent.CLOSE_SIGN_UP -> {
                    router.closeSignUp()
                    viewModel.routingEvent.value = null
                }
                SignUpVMRoutingEvent.SHOW_MAIN -> {

                }
            }
        })
    }

    private fun initUI() {
    }
}

object SignUpFragmentBindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }
}


//import android.os.Bundle
//import android.util.Log
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import com.example.heart_to_heart.R
//import com.example.heart_to_heart.presentation.base.BaseFragment
//import com.google.android.material.textfield.TextInputLayout
//import kotlinx.android.synthetic.main.fragment_sign_up.*
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class SignUpFragment : BaseFragment() {
//
//    private val viewModel: SignUpViewModel by viewModel()
//
//    private lateinit var textInputEmail: TextInputLayout
//    private lateinit var textInputFirstName: TextInputLayout
//    private lateinit var textInputLastName: TextInputLayout
//    private lateinit var textInputNickname: TextInputLayout
//    private lateinit var textInputPassword: TextInputLayout
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        this.initBinding()
//        this.initUI()
//
//        fragment_sign_up_til_email.editText?.setText("benzema@gmail.com")
//        fragment_sign_up_til_fisrtname.editText?.setText("Karim")
//        fragment_sign_up_til_lastname.editText?.setText("Benzema")
//        fragment_sign_up_til_nickname.editText?.setText("MADRID_9")
//        fragment_sign_up_til_password.editText?.setText("12345")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("YOLO", "onDestroy() from SignUpFragment")
//    }
//
//    private fun initBinding() {
//        this.viewModel.didRouterChange.subscribe { option ->
//            when(option) {
//                SignUpFragmentRoutingOptions.CLOSE -> {
//                    var toast = Toast.makeText(activity, "Successfully Signed Up.", Toast.LENGTH_LONG)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
//                    router.closeSignUp()
//                }
//                SignUpFragmentRoutingOptions.SHOW_ALREADY_SIGNED_UP -> {
//                    var toast = Toast.makeText(activity, "Already existed email", Toast.LENGTH_LONG)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
//                }
//                SignUpFragmentRoutingOptions.SHOW_NETWORK_ERROR -> {
//                    var toast = Toast.makeText(activity, "NETWORK ERROR", Toast.LENGTH_LONG)
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
//                }
//            }
//        }.apply { disposables.add(this) }
//    }
//
//    override fun onDestroyView() {
//        disposables.clear()
//        super.onDestroyView()
//    }
//
//    private fun initUI() {
//        fragment_sign_up_btn_signsup.setOnClickListener {
//            // this.viewModel.test()
//            // router.closeSignUp()
//            confirmInput()
//            this.signUp()
//        }
//        fragment_sign_up_btn_login.setOnClickListener { router.closeSignUp() }
//    }
//
//    private fun signUp() {
//        val email = fragment_sign_up_til_email.editText?.text.toString().trim()
//        val firstName = fragment_sign_up_til_fisrtname.editText?.text.toString().trim()
//        val lastName = fragment_sign_up_til_lastname.editText?.text.toString().trim()
//        val nickname = fragment_sign_up_til_nickname.editText?.text.toString().trim()
//        val password = fragment_sign_up_til_password.editText?.text.toString().trim()
//        this.viewModel.signUp(email, firstName, lastName, nickname, password)
//    }
//
//    private fun validateEmail(): Boolean {
//        var emailValue = fragment_sign_up_til_email.editText?.text.toString().trim()
//
//        if (emailValue.isEmpty()) {
//            fragment_sign_up_til_email.error = "Field can't be empty"
//            return false
//        } else {
//            fragment_sign_up_til_email.error = null
//            return true
//        }
//    }
//
//    private fun validateFirstname(): Boolean {
//        var firstnameValue = fragment_sign_up_til_fisrtname.editText?.text.toString().trim()
//        if (firstnameValue.isEmpty()) {
//            fragment_sign_up_til_fisrtname.error = "Field can't be empty"
//            return false
//        } else {
//            fragment_sign_up_til_fisrtname.error = null
//            return true
//        }
//    }
//
//    private fun validateLastname(): Boolean {
//        var lastnameValue = fragment_sign_up_til_lastname.editText?.text.toString().trim()
//        if (lastnameValue.isEmpty()) {
//            fragment_sign_up_til_lastname.error = "Field can't be empty"
//            return false
//        } else {
//            fragment_sign_up_til_lastname.error = null
//            return true
//        }
//    }
//
//    private fun validateNickname(): Boolean {
//        var nicknameValue = fragment_sign_up_til_nickname.editText?.text.toString().trim()
//        if (nicknameValue.isEmpty()) {
//            fragment_sign_up_til_nickname.error = "Field can't be empty"
//            return false
//        } else if (nicknameValue.length > 15) {
//            fragment_sign_up_til_nickname.error = "Nickname too long"
//            return false
//        } else {
//            fragment_sign_up_til_nickname.error = null
//            return true
//        }
//    }
//
//    private fun validatePassword(): Boolean {
//        var passwordValue = fragment_sign_up_til_password.editText?.text.toString().trim()
//        if (passwordValue.isEmpty()) {
//            fragment_sign_up_til_password.error = "Field can't be empty"
//            return false
//        } else {
//            fragment_sign_up_til_password.error = null
//            return true
//        }
//    }
//
//    fun confirmInput() {
//        // if (!validateEmail() || !validateFirstname() || !validateLastname() || !validateNickname() || !validatePassword()) {
//        if (!validateEmail() or !validateFirstname() or !validateLastname() or !validateNickname() or !validatePassword()) {
//            return
//        }
//    }
//}