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
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initUI()
    }

    private fun initBinding() {
        viewModel.routingEvent.observe(this.viewLifecycleOwner, Observer {

        })
    }

    private fun initUI() {
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_main_nvf) as? NavHostFragment
        var navController = navHostFragment?.navController
        fragment_main_bnv.setupWithNavController(navController!!)
    }
}