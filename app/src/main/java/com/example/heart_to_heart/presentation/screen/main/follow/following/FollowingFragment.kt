package com.example.heart_to_heart.presentation.screen.main.follow.following

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentFollowingBinding
import com.example.heart_to_heart.presentation.screen.main.base.BaseMainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment : BaseMainFragment() {

    private val viewModel: FollowingViewModel by viewModel()
    private lateinit var binding: FragmentFollowingBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: FollowingRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YOLO", "FollowingFragment: onCreate()")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("YOLO", "FollowingFragment: onCreateView()")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_following, container, false)
        val rootView = binding.root
        recyclerView = rootView.findViewById(R.id.fragment_following_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
        initUI()
        initBinding()
    }

    override fun onDestroyView() {
        Log.d("YOLO", "FollowingFragment: onDestroyView()")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("YOLO", "FollowerFragment: onDestroy()")
        super.onDestroy()
    }

    private fun initUI() {
        initRecyclerView()
    }

    private fun initBinding() {

    }

    private fun initRecyclerView() {
        recyclerViewAdapter = FollowingRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    inner class FollowingRecyclerViewAdapter
    constructor(

    ) : RecyclerView.Adapter<FollowingRecyclerViewAdapter.FollowingItemViewHolder>() {

        inner class FollowingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind() {

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_following_item, parent, false)
            return FollowingItemViewHolder(view)
        }

        override fun getItemCount(): Int = 10

        override fun onBindViewHolder(holder: FollowingItemViewHolder, position: Int) {
            holder.bind()
        }
    }
}