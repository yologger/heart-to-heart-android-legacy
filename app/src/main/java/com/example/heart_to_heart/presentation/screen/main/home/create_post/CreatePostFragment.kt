package com.example.heart_to_heart.presentation.screen.main.home.create_post

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.heart_to_heart.R
import com.example.heart_to_heart.databinding.FragmentCreatePostBinding
import com.example.heart_to_heart.presentation.base.BaseFragment
import gun0912.tedimagepicker.builder.TedImagePicker
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_create_post.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class CreatePostFragment : BaseFragment() {

    private val viewModel: CreatePostViewModel by viewModel()
    private lateinit var binding: FragmentCreatePostBinding

    private lateinit var toolbar: Toolbar
    private lateinit var galleryButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false)
        val rootView = binding.root
        toolbar = rootView.findViewById<Toolbar>(R.id.fragment_create_post_tb)
        galleryButton = rootView.findViewById<Button>(R.id.fragment_create_post_btn_gallery)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.fragment_create_post_rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initUI()
        this.initBinding()
    }

    private fun initUI() {
        // Initialize Toolbar
        toolbar.inflateMenu(R.menu.menu_fragment_create_post)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24)
        toolbar.setNavigationOnClickListener { router.closeCreatePost() }
        toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.menu_fragment_create_post_action_post -> { post() }
                else -> { }
            }
            true
        }

        // Initialize RecyclerView
        recyclerViewAdapter = RecyclerViewAdapter(viewModel.imageUris, this)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel

        viewModel.imageUrisLiveData.observe(this.viewLifecycleOwner, Observer { imageUris ->
            recyclerViewAdapter.updateList(imageUris)
        })

        galleryButton.setOnClickListener {
            TedImagePicker.with(activity as Context)
                .startMultiImage { uris ->
                    viewModel.imageUris.addAll(uris)
                    viewModel.imageUrisLiveData.value = viewModel.imageUris
                }
        }

        viewModel.routingEvent.observe(this.viewLifecycleOwner, Observer { event ->
            when(event) {
                null -> { }
                CreatePostVMRoutingEvent.CLOSE -> {
                    viewModel.routingEvent.setValue(null)
                    router.closeCreatePost()
                }
                CreatePostVMRoutingEvent.UNKNOWN_ERROR -> {
                    var toast = Toast.makeText(activity, "UNKNOWN ERROR", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    viewModel.routingEvent.setValue(null)

                }
            }
        })
    }

    private fun post() {
        this.viewModel.post()
    }

    inner class RecyclerViewAdapter
    constructor(
        private var imageUris: MutableList<Uri>,
        private var fragment: CreatePostFragment
    ) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(uri: Uri) {
                val imageView = itemView.findViewById<ImageView>(R.id.item_fragment_create_post_image_with_clear_button_img)
                Glide.with(fragment)
                    .load(uri)
                    .into(imageView)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fragment_create_post_image_with_clear_button, parent, false)
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
}


