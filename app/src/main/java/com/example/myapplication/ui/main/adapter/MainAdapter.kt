package com.example.myapplication.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsPhoto

class MainAdapter:
    ListAdapter<PexelsPhoto, MainAdapter.ViewHolderPhotos>(PhotosDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhotos {
        return ViewHolderPhotos(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderPhotos, position: Int) {
        holder.bindData(getItem(position), position)
    }

    inner class ViewHolderPhotos(view: View) : RecyclerView.ViewHolder(view) {

        private val imageViewPhoto = itemView.findViewById<ImageView>(R.id.image_view_photo)

        fun bindData(pexelPhoto: PexelsPhoto, position: Int) {
            Glide.with(imageViewPhoto)
                .load(pexelPhoto.src.portrait)
                .into(imageViewPhoto)
        }
    }
}