package com.example.heart_to_heart.presentation.screen.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.screen.main.follow.FollowFragment
import com.example.heart_to_heart.presentation.screen.main.home.HomeFragment
import com.example.heart_to_heart.presentation.screen.main.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_main_nvf) as? NavHostFragment
        var navController = navHostFragment?.navController
        fragment_main_bnv.setupWithNavController(navController!!)
    }

//    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
//        when (menuItem.itemId) {
//            R.id.menu_main_bottom_navigation_home -> {
//                Log.d("YOLO", "HOME TAB")
//                return true
//            }
//            R.id.menu_main_bottom_navigation_follow -> {
//                Log.d("YOLO", "FOLLOW TAB")
//                return true
//            }
//            R.id.menu_main_bottom_navigation_profile -> {
//                Log.d("YOLO", "PROFILE TAB")
//                return true
//            }
//        }
//        return false
//    }
}