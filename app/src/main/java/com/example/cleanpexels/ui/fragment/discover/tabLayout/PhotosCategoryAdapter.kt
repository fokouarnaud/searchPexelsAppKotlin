package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanpexels.R
import com.example.cleanpexels.data.model.PexelsPhotoCategory
import com.example.cleanpexels.databinding.CategoryPhotosItemBinding

class PhotosCategoryAdapter(
    private val lifecycle: Lifecycle,
    private val viewLifecycleOwner: LifecycleOwner
) :
    ListAdapter<PexelsPhotoCategory,
            PhotosCategoryAdapter.ViewHolderPhotosCategory>(PhotosCategoryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPhotosCategory {
        val dataBinding: CategoryPhotosItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_photos_item,
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

    inner class ViewHolderPhotosCategory(private val dataBinding: CategoryPhotosItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bindData(
            pexelsPhotoCategory: PexelsPhotoCategory,
            position: Int
        ) {
            val photosAdapter = PhotosAdapter()

            dataBinding.titleTextView.text = pexelsPhotoCategory.name.capitalize()

            dataBinding.photosRecyclerview.apply {
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
            dataBinding.titleTextView.text = ""
        }
    }
}