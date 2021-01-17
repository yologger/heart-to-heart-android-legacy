package com.example.heart_to_heart.presentation.screen.main.follow.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentFollowerBinding
import com.example.heart_to_heart.presentation.screen.main.base.BaseMainFragment

class FollowerFragment : BaseMainFragment() {

    private val viewModel: FollowerViewModel by viewModel()
    private lateinit var binding: FragmentFollowerBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: FollowerRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follower, container, false)
        val rootView = binding.root
        recyclerView = rootView.findViewById(R.id.fragment_follower_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
        initUI()
        initBinding()
    }

    private fun initUI() {
        initRecyclerView()
    }

    private fun initBinding() {

    }

    private fun initRecyclerView() {
        recyclerViewAdapter = FollowerRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
    }

    inner class FollowerRecyclerViewAdapter
    constructor(

    ) : RecyclerView.Adapter<FollowerRecyclerViewAdapter.FollowerItemViewHolder>() {

        inner class FollowerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind() {

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_follower_item, parent, false)
            return FollowerItemViewHolder(view)
        }

        override fun getItemCount(): Int = 5

        override fun onBindViewHolder(holder: FollowerItemViewHolder, position: Int) {
            holder.bind()
        }
    }
}