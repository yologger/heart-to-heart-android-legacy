package com.example.heart_to_heart.presentation.screen.main.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.screen.main.follow.follower.FollowerFragment
import com.example.heart_to_heart.presentation.screen.main.follow.following.FollowingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowFragment : BaseFragment() {

    private val viewModel: FollowViewModel by viewModel()

    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_follow, container, false)
        viewPager2 = rootView.findViewById(R.id.fragment_follow_vp)
        tabLayout = rootView.findViewById(R.id.fragment_follow_tl)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllPost()
        initUI()
        initBinding()
    }
    private val tabTextList = arrayListOf("FOLLOWING", "FOLLOWER")


    private fun initUI() {
        viewPager2.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }

    private fun initBinding() {

    }


    inner class ViewPagerAdapter
    constructor(
        fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> FollowingFragment()
                else -> FollowerFragment()
            }
        }

    }
}