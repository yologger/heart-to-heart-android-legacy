package com.example.heart_to_heart.presentation.screen.main.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_follow.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowFragment : BaseFragment() {

    private val viewModel: FollowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initUI()
    }

    fun initBinding() {

    }

    fun initUI() {
        fragment_follow_btn.setOnClickListener {
            viewModel.getAllPost()
        }
    }
}