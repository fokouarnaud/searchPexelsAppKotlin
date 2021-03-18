package com.example.cleanpexels.ui.fragment.discover.tabLayout

import androidx.recyclerview.widget.DiffUtil
import com.example.cleanpexels.data.model.PexelsPhotoCategory

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