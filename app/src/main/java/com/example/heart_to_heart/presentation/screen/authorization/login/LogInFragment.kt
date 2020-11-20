package com.example.heart_to_heart.presentation.screen.authorization.login

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
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
                null -> { }
                LogInVMRoutingEvent.SHOW_SIGN_UP -> {
                    viewModel.routingEvent.setValue(null)
                    router.showSignUp()
                }
                LogInVMRoutingEvent.SHOW_FIND_PASSWORD -> {

                }
                LogInVMRoutingEvent.NETWORK_CONNECTION_ERROR -> {
                    var toast = Toast.makeText(activity, "NETWORK CONNECTION ERROR", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)
                }
                LogInVMRoutingEvent.INVALID_EMAIL_ERROR -> {
                    var toast = Toast.makeText(activity, "INVALID EMAIL", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)
                }
                LogInVMRoutingEvent.INVALID_PASSWORD_ERROR -> {
                    var toast = Toast.makeText(activity, "INVALID PASSWORD", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)
                }
                LogInVMRoutingEvent.SHOW_MAIN -> {
                    viewModel.routingEvent.setValue(null)
                    router.showMain()
                }
                LogInVMRoutingEvent.UNKNOWN_ERROR -> {

                }
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
}