package com.example.heart_to_heart.presentation.screen.main.profile

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.screen.authorization.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initBinding()
        this.initUI()
    }

    private fun initBinding() {
        fragment_profile_btn_logout.setOnClickListener {
            this.viewModel.logOut()
            // router.closeMain()
        }
    }


    private fun initUI() {
        viewModel.routingEvent.observe(this.viewLifecycleOwner, Observer { event ->
            when(event) {
                ProfileVMRoutingEvent.SHOW_LOGIN -> {
                    var toast = Toast.makeText(activity, "SUCCESSFULLY LOG OUT", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    router.showLogIn()
                }
                else -> {}
            }
        })
    }
}