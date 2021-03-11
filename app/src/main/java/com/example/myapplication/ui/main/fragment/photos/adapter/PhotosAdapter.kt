package com.example.myapplication.ui.main.fragment.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsPhoto
import com.example.myapplication.databinding.ItemCategoryPhotoBinding
import com.example.myapplication.databinding.ItemPhotoBinding

class PhotosAdapter :
    PagingDataAdapter<PexelsPhoto, PhotosAdapter.ViewHolderPhotos>(PhotosDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhotos {
        val dataBinding: ItemPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photo,
            parent, false
        )

        return ViewHolderPhotos(
           dataBinding
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

    inner class ViewHolderPhotos(private val dataBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {


        fun bindData(pexelPhoto: PexelsPhoto) {
            Glide.with(dataBinding.imageViewPhoto)
                .load(pexelPhoto.src.portrait)
                .into(dataBinding.imageViewPhoto)
            dataBinding.apply {
                photographerPhoto=pexelPhoto.photographer
                widthHeightPhoto= "${pexelPhoto.height.toString()} " +
                        "x ${ pexelPhoto.width.toString()} "

            }
        }

        fun bindPlaceholder() {
            Glide.with(dataBinding.imageViewPhoto)
                .load("")
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(dataBinding.imageViewPhoto)
        }


    }
}