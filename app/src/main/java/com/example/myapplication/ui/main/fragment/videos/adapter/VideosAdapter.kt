package com.example.myapplication.ui.main.fragment.videos.adapter

import android.util.Log
import android.util.Log.ERROR
import android.util.Log.VERBOSE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsVideo

class VideosAdapter :
    ListAdapter<PexelsVideo, VideosAdapter.ViewHolderVideos>(VideosDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideos {
        return ViewHolderVideos(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_video,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderVideos, position: Int) {
        holder.bindData(getItem(position), position)
    }

    inner class ViewHolderVideos(view: View) : RecyclerView.ViewHolder(view) {

        private val imageViewVideo = itemView.findViewById<ImageView>(R.id.image_view_video)

        fun bindData(pexelsVideo: PexelsVideo, position: Int) {
            Glide.with(imageViewVideo)
                .load(pexelsVideo.image)
                .into(imageViewVideo)
        }
    }
}