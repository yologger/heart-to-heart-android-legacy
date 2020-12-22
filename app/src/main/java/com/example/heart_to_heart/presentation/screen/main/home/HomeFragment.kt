package com.example.heart_to_heart.presentation.screen.main.home

import android.os.Bundle
import android.util.Log
import android.view.*
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initUI()
    }

    private fun initBinding() {

    }

    private fun initUI() {
        this.initToolbar()
        this.initSearchView()
    }

    private fun initToolbar() {
        fragment_home_tb.inflateMenu(R.menu.menu_fragment_home)
        fragment_home_tb.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.menu_fragment_home_action_post -> {
                    Log.d("YOLO", "button clicked!")
                    router.showCreatePost()
                }
                else -> {

                }
            }
            false
        }
    }

    private fun initSearchView() {

        fragment_home_sv.setOnClickListener {
            fragment_home_sv.isIconified = false
        }

        fragment_home_sv.setOnSearchClickListener {
            Log.d("YOLO", "setOnSearchClickListener")
            // fragment_home_btn.visibility = View.INVISIBLE
        }

        fragment_home_sv.setOnCloseListener {
            Log.d("YOLO", "setOnCloseListener")
            // fragment_home_btn.visibility = View.VISIBLE
            fragment_home_sv.clearFocus()
            return@setOnCloseListener false
        }
    }
}