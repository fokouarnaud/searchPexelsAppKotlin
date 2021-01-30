package com.example.myapplication.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.data.model.PexelsPhoto

class PhotosDiffCallBack : DiffUtil.ItemCallback<PexelsPhoto>() {
    override fun areItemsTheSame(oldItem: PexelsPhoto, newItem: PexelsPhoto): Boolean {
        return oldItem==newItem
    }
    override fun areContentsTheSame(oldItem: PexelsPhoto, newItem: PexelsPhoto): Boolean {
        return  oldItem== newItem
    }
}