package com.example.heart_to_heart.presentation.screen.main.home

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentHomeBinding
import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.model.Post
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val rootView = binding.root
        toolbar = rootView.findViewById<Toolbar>(R.id.fragment_home_tb)
        searchView = rootView.findViewById<SearchView>(R.id.fragment_home_sv)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.fragment_home_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initBinding()
        viewModel.getPosts()
    }

    private fun initUI() {
        initToolbar()
        initSearchView()
        initRecyclerView()
    }

    private fun initBinding() {
        viewModel.postsLiveData.observe(this.viewLifecycleOwner, Observer { posts ->
            recyclerViewAdapter.updateList(posts)
        })

        viewModel.isLoading.observe(this.viewLifecycleOwner, Observer { isLoading ->
            Log.d("YOLO", "isLoading from observer: ${isLoading}")
            if (isLoading) {
                recyclerViewAdapter.showLoadingView()
            } else {
                recyclerViewAdapter.hideLoadingView()
            }
        })
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_fragment_home)
        toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.menu_fragment_home_action_post -> {
                    router.showCreatePost()
                }
                else -> {
                }
            }
            false
        }
    }

    private fun initSearchView() {
        searchView.setOnClickListener { fragment_home_sv.isIconified = false }
        searchView.setOnSearchClickListener {
            // fragment_home_btn.visibility = View.INVISIBLE
        }
        searchView.setOnCloseListener {
            // fragment_home_btn.visibility = View.VISIBLE
            fragment_home_sv.clearFocus()
            return@setOnCloseListener false
        }
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(viewModel.posts, this)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(InfiniteScrollingListener(layoutManager))
    }


    class RecyclerViewAdapter
    constructor(
        private var posts: MutableList<Post?>,
        private var fragment: HomeFragment
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            private const val VIEW_TYPE_ITEM = 0
            private const val VIEW_TYPE_LOADING = 1
        }

        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private var textViewEmail: TextView = itemView.findViewById<TextView>(R.id.fragment_home_item_post_tv_email)
            private var imageViewAvatar: ImageView = itemView.findViewById<ImageView>(R.id.fragment_home_item_post_iv_avatar)
            private var textViewContent: TextView = itemView.findViewById<TextView>(R.id.fragment_home_item_post_tv_content)


            fun bind(post: Post) {
                textViewContent.text = post.content
                imageViewAvatar.setImageResource(R.drawable.avatar_default)
            }
        }

        inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind() {

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                VIEW_TYPE_ITEM -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item_post, parent, false)
                    ItemViewHolder(view)
                }
                VIEW_TYPE_LOADING -> {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item_loading, parent, false)
                    LoadingViewHolder(view)
                }
                else -> throw RuntimeException("VIEW TYPE ERROR")
            }
        }

        override fun getItemCount(): Int {
            return posts.size
        }

        override fun getItemViewType(position: Int): Int {
            return if (posts[position] == null) {
                VIEW_TYPE_LOADING
            } else {
                VIEW_TYPE_ITEM
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder.itemViewType == VIEW_TYPE_ITEM) {
                (holder as ItemViewHolder).bind(posts[position]!!)
            } else {
                (holder as LoadingViewHolder).bind()
            }
        }

        fun showLoadingView() {
            posts.add(null)
            notifyItemInserted(posts.size - 1)
        }

        fun hideLoadingView() {
            if (posts.size != 0) {
                posts.removeAt(posts.size - 1)
                notifyItemRemoved(posts.size)
            }
        }

        fun updateList(posts: MutableList<Post?>) {
            this.posts = posts
            notifyDataSetChanged()
        }
    }


    private var totalItemCount: Int = 0
    private var lastVisibleItemPosition: Int = 0
    private var visibleThreshold: Int = 5
    // We set the visibleThreshold to 5, which means the circular progress bar will show up when the user sees the 5th item from the end of our downloaded data.

    // private var isLoading: Boolean = false

    inner class InfiniteScrollingListener : RecyclerView.OnScrollListener {

        private var layoutManager: RecyclerView.LayoutManager

        constructor(layoutManager: LinearLayoutManager) {
            this.layoutManager = layoutManager
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy <= 0) return

            totalItemCount = layoutManager.itemCount

            if (layoutManager is StaggeredGridLayoutManager) {
                //
            } else if (layoutManager is GridLayoutManager) {
                //
            } else if (layoutManager is LinearLayoutManager) {
                lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }

            if (!viewModel.isLoading.value!! && totalItemCount <= lastVisibleItemPosition + visibleThreshold) {
                viewModel.getPosts()
            }
        }
    }
}