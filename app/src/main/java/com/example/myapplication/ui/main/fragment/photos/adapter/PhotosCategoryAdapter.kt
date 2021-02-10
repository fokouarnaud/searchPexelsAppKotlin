package com.example.myapplication.ui.main.fragment.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.PexelsPhotoCategory
import com.example.myapplication.databinding.ItemCategoryPhotoBinding

class PhotosCategoryAdapter(
    private val lifecycle: Lifecycle,
    private val viewLifecycleOwner: LifecycleOwner
) :
    ListAdapter<PexelsPhotoCategory,
            PhotosCategoryAdapter.ViewHolderPhotosCategory>(PhotosCategoryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhotosCategory {
        val dataBinding: ItemCategoryPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category_photo,
            parent, false
        )

        return ViewHolderPhotosCategory(
            dataBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolderPhotosCategory, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bindData(item, position)
        } else {
            holder.bindPlaceholder()
        }

    }

    inner class ViewHolderPhotosCategory(private val dataBinding: ItemCategoryPhotoBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bindData(
            pexelsPhotoCategory: PexelsPhotoCategory,
            position: Int
        ) {
            val photosAdapter = PhotosAdapter()

            dataBinding.textViewCategoryName.text = pexelsPhotoCategory.name

            dataBinding.recyclerviewPhoto.apply {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = photosAdapter.withLoadStateFooter(
                    footer = PhotosLoadStateAdapter { photosAdapter.retry() }
                )
            }

            pexelsPhotoCategory.liveDataPagingDataPexelsPhoto.observe(
                viewLifecycleOwner, {
                    photosAdapter.submitData(lifecycle, it)
                }
            )
        }

        fun bindPlaceholder() {
            dataBinding.textViewCategoryName.text = ""
        }
    }
}