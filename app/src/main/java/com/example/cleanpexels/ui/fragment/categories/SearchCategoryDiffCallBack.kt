package com.example.cleanpexels.ui.fragment.categories

import androidx.recyclerview.widget.DiffUtil
import com.example.cleanpexels.data.model.SearchCategory

class SearchCategoryDiffCallBack : DiffUtil.ItemCallback<SearchCategory>() {
    override fun areItemsTheSame(oldItem: SearchCategory, newItem: SearchCategory): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SearchCategory, newItem: SearchCategory): Boolean {
        return oldItem == newItem
    }

}
