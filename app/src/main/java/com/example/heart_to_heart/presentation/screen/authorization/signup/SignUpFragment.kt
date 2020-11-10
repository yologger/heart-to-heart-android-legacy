package com.example.heart_to_heart.presentation.screen.authorization.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.screen.authorization.login.LogInViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment() {

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initBinding()
        this.initUI()
    }

    private fun initBinding() {
        this.viewModel.test()
    }

    private fun initUI() {
        fragment_sign_up_btn_signsup.setOnClickListener { router.closeSignUp() }
        fragment_sign_up_btn_login.setOnClickListener { router.closeSignUp() }
    }
}