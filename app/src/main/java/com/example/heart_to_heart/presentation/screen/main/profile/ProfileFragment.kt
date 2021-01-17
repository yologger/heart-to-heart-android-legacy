package com.example.heart_to_heart.presentation.screen.main.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heart_to_heart.R
import com.example.heart_to_heart.application.Constants.Companion.BASE_URL
import com.example.heart_to_heart.databinding.FragmentProfileBinding
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.screen.authorization.signup.SignUpViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    private val viewModel: ProfileViewModel by viewModel()

    private lateinit var binding: FragmentProfileBinding

    private lateinit var nicknameItemView: ConstraintLayout
    private lateinit var passwordItemView: ConstraintLayout
    private lateinit var logoutItemView: ConstraintLayout
    private lateinit var avatarImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YOLO", "ProfileFragment: onCreate()")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("YOLO", "ProfileFragment: onCreateView()")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val rootView = binding.root
        // recyclerView = rootView.findViewById(R.id.fragment_profile_rv)
        nicknameItemView = rootView.findViewById(R.id.fragment_profile_ll_nickname)
        passwordItemView = rootView.findViewById(R.id.fragment_profile_ll_password)
        logoutItemView = rootView.findViewById(R.id.fragment_profile_ll_logout)
        avatarImageView = rootView.findViewById(R.id.fragment_profile_iv_avatar)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initUI()
        this.initBinding()
    }

    override fun onDestroyView() {
        Log.d("YOLO", "ProfileFragment: onDestroyView()")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("YOLO", "ProfileFragment: onDestroy()")
        super.onDestroy()
    }

    private fun initUI() {
        nicknameItemView.setOnClickListener {
            Log.d("YOLO", "NICKNAME!!!")
        }

        passwordItemView.setOnClickListener {
            Log.d("YOLO", "PASSWORD!!!")
        }

        logoutItemView.setOnClickListener { this.viewModel.logOut() }

        avatarImageView.setOnClickListener {
            TedImagePicker.with(activity as Context)
                .start { uri ->
                    viewModel.uploadAvatarImage(uri)
                }
        }
    }

    private fun initBinding() {
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

        viewModel.avatarImageLiveData.observe(this.viewLifecycleOwner, Observer { uri ->
            val url = "${BASE_URL}/${uri}"
            when (uri) {
                null -> {
                    Glide.with(this)
                        .load(R.drawable.avatar_default)
                        .into(avatarImageView)
                }
                else -> {
                    Glide.with(this)
                        .load(url)
                        .into(avatarImageView)
                }
            }
        })
    }
}