package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanpexels.R
import com.example.cleanpexels.data.model.PexelsPhoto
import com.example.cleanpexels.databinding.PhotoItemBinding

class PhotosAdapter :
    PagingDataAdapter<PexelsPhoto, PhotosAdapter.ViewHolderPhotos>(PhotosDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhotos {
        val dataBinding: PhotoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent, false
        )

        return ViewHolderPhotos(
            dataBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolderPhotos, position: Int) {
        val item = getItem(position)
        if (item == null) {
            holder.bindPlaceholder()
        } else {
            holder.bindData(item)
        }
    }

    inner class ViewHolderPhotos(private val dataBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {


        fun bindData(pexelPhoto: PexelsPhoto) {
            Glide.with(dataBinding.imageViewPhoto)
                .load(pexelPhoto.src.portrait)
                .into(dataBinding.imageViewPhoto)
            dataBinding.apply {
                photographerPhoto = pexelPhoto.photographer
                widthHeightPhoto = "${pexelPhoto.height} " +
                        "x ${pexelPhoto.width} "

            }
        }

        fun bindPlaceholder() {
            Glide.with(dataBinding.imageViewPhoto)
                .load("")
                .placeholder(R.drawable.ic_round_image_24)
                .into(dataBinding.imageViewPhoto)
        }


    }
}