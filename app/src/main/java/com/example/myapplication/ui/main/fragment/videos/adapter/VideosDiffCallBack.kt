package com.example.myapplication.ui.main.fragment.videos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.data.model.PexelsVideo

class VideosDiffCallBack : DiffUtil.ItemCallback<PexelsVideo>() {
    override fun areItemsTheSame(oldItem: PexelsVideo, newItem: PexelsVideo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PexelsVideo, newItem: PexelsVideo): Boolean {
        return oldItem == newItem
    }
}