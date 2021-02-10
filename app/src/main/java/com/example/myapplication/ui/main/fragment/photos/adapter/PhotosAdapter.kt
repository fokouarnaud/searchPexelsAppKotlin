package com.example.myapplication.ui.main.fragment.photos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsPhoto

class PhotosAdapter :
    PagingDataAdapter<PexelsPhoto, PhotosAdapter.ViewHolderPhotos>(PhotosDiffCallBack()) {

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
        val item=getItem(position)
        if(item==null){
            holder.bindPlaceholder()
        }else{
            holder.bindData(item)
        }
    }

    inner class ViewHolderPhotos(view: View) : RecyclerView.ViewHolder(view) {

        private val imageViewPhoto = itemView.findViewById<ImageView>(R.id.image_view_photo)

        fun bindData(pexelPhoto: PexelsPhoto) {
            Glide.with(imageViewPhoto)
                .load(pexelPhoto.src.portrait)
                .into(imageViewPhoto)
        }

        fun bindPlaceholder() {
            Glide.with(imageViewPhoto)
                .load("")
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(imageViewPhoto)
        }


    }
}