package com.example.myapplication.ui.main.fragment.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.data.model.PexelsPhotoCategory

class PhotosCategoryDiffCallBack : DiffUtil.ItemCallback<PexelsPhotoCategory>() {
    override fun areItemsTheSame(
        oldItem: PexelsPhotoCategory,
        newItem: PexelsPhotoCategory
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PexelsPhotoCategory,
        newItem: PexelsPhotoCategory
    ): Boolean {
        return oldItem == newItem
    }
}