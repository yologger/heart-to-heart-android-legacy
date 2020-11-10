package com.example.heart_to_heart.presentation.screen.authorization.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_log_in.*
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
        this.viewModel.test()
    }

    private fun initUI() {
        fragment_log_in_btn_login.setOnClickListener { router.showMain() }
        fragment_log_in_btn_signup.setOnClickListener { router.showSignUp() }
    }
}