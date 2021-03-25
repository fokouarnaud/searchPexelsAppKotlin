package com.example.cleanpexels.ui.fragment.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanpexels.R
import com.example.cleanpexels.data.model.SearchCategory
import com.example.cleanpexels.databinding.CategoryItemBinding

class CategoriesFragmentAdapter :
    ListAdapter<SearchCategory, CategoriesFragmentAdapter.ViewHolderSearchCategory>(
        SearchCategoryDiffCallBack()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderSearchCategory {

        val dataBinding: CategoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_item,
            parent, false
        )

        return ViewHolderSearchCategory(
            dataBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolderSearchCategory, position: Int) {
        holder.bindData(getItem(position), position)
    }

    inner class ViewHolderSearchCategory(private val dataBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bindData(searchCategory: SearchCategory, position: Int) {
            dataBinding.imageViewCategory.setImageResource(searchCategory.srcImage)

            dataBinding.apply {
                nameCategory = searchCategory.name
            }
        }
    }
}