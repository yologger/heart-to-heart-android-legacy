package com.example.heart_to_heart.presentation.screen.main.home.create_post

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentCreatePostBinding
import com.example.heart_to_heart.presentation.screen.AppActivity
import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationActivity
import com.example.heart_to_heart.presentation.screen.main.MainActivity
import com.example.heart_to_heart.presentation.screen.main.base.BaseMainFragment
import com.example.heart_to_heart.presentation.screen.main.PostViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment : BaseMainFragment() {

    private val viewModel: CreatePostViewModel by viewModel()
    private val postViewModel: PostViewModel by sharedViewModel()

    private lateinit var binding: FragmentCreatePostBinding

    private lateinit var toolbar: Toolbar
    private lateinit var galleryButton: ImageButton
    private lateinit var editTextContent: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false)
        val rootView = binding.root
        toolbar = rootView.findViewById<Toolbar>(R.id.fragment_create_post_tb)
        editTextContent = rootView.findViewById(R.id.fragment_create_post_ed)
        galleryButton = rootView.findViewById<ImageButton>(R.id.fragment_create_post_btn_gallery)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.fragment_create_post_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initUI()
        this.initBinding()
        viewModel.test()
    }

    private fun initUI() {
        initToolbar()
        initRecyclerView()
        (activity as MainActivity).hideBottomTabView()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = this.postViewModel

        postViewModel.imageUrisLiveData.observe(this.viewLifecycleOwner, Observer { imageUris ->
            recyclerViewAdapter.updateList(imageUris)
        })

        galleryButton.setOnClickListener {
            TedImagePicker.with(activity as Context)
                .startMultiImage { uris ->
                    postViewModel.setImages(uris)
                }
        }

        postViewModel.createPostActivityRoutingEvent.observe(this.viewLifecycleOwner, Observer { event ->
            when(event) {
                null -> { }
                CreatePostActivityRoutingEvent.CLOSE -> {
                    (activity as MainActivity)?.hideKeyboard()
                    postViewModel.createPostActivityRoutingEvent.setValue(null)
                    router.closeCreatePost()
                }
                CreatePostActivityRoutingEvent.UNKNOWN_ERROR -> {
                    var toast = Toast.makeText(activity, "UNKNOWN ERROR", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    postViewModel.createPostActivityRoutingEvent.setValue(null)
                }
            }
        })
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_fragment_create_post)
        toolbar.setNavigationIcon(R.drawable.icon_close_24_white)
        toolbar.setNavigationOnClickListener {
            hideKeyboard()
            router.closeCreatePost()
        }
        toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.menu_fragment_create_post_action_post -> { post() }
                else -> { }
            }
            true
        }
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(postViewModel.imageUris, this)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
    }

    private fun post() {
        this.postViewModel.createPost()
    }

    inner class RecyclerViewAdapter
    constructor(
        private var imageUris: MutableList<Uri>,
        private var fragment: CreatePostFragment
    ) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(uri: Uri) {
                val imageView = itemView.findViewById<ImageView>(R.id.fragment_create_post_item_image_iv)
                Glide.with(fragment)
                    .load(uri)
                    .transform(CenterCrop(), RoundedCorners(16))
                    .into(imageView)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_create_post_item_image, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return imageUris.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.bind(imageUris[position])
        }

        fun updateList(imageUris: MutableList<Uri>) {
            this.imageUris = imageUris
            notifyDataSetChanged()
        }
    }

    private fun hideKeyboard() = (activity as MainActivity)?.hideKeyboard()

    override fun onDestroyView() {
        (activity as MainActivity)?.showBottomTabView()
        super.onDestroyView()
    }
}


