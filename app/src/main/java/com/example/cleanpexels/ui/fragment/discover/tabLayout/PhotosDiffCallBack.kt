package com.example.cleanpexels.ui.fragment.discover.tabLayout

import androidx.recyclerview.widget.DiffUtil
import com.example.cleanpexels.data.model.PexelsPhoto

class PhotosDiffCallBack : DiffUtil.ItemCallback<PexelsPhoto>() {
    override fun areItemsTheSame(oldItem: PexelsPhoto, newItem: PexelsPhoto): Boolean {
        return oldItem==newItem
    }
    override fun areContentsTheSame(oldItem: PexelsPhoto, newItem: PexelsPhoto): Boolean {
        return  oldItem== newItem
    }
}