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
            when (event) {
                SignUpVMRoutingEvent.CLOSE -> {
                    var toast = Toast.makeText(activity, "SUCCESSFULLY SIGNED UP.", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    router.closeSignUp()
                    viewModel.routingEvent.setValue(null)
                }
                SignUpVMRoutingEvent.SHOW_ALREADY_SIGNED_UP_ERROR -> {
                    var toast = Toast.makeText(activity, "ALREADY SIGNED UP EMAIL", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)
                }
                SignUpVMRoutingEvent.SHOW_NETWORK_ERROR -> {
                    var toast = Toast.makeText(activity, "NETWORK ERROR", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)
                }
                SignUpVMRoutingEvent.SHOW_UNKNOWN_ERROR -> {
                    var toast = Toast.makeText(activity, "UNKNOWN ERROR", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)
                }
                null -> {
                }
            }
        })
    }

    private fun initUI() {}
}