package com.example.heart_to_heart.presentation.screen.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {


    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        bottomNavigationView = rootView.findViewById(R.id.fragment_main_bnv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initUI()
    }

    private fun initBinding() {
    }

    private fun initUI() {
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_main_nvf) as? NavHostFragment
        val navController = navHostFragment?.navController
        fragment_main_bnv.setupWithNavController(navController!!)
    }
}