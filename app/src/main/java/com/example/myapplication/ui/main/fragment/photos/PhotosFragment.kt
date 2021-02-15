package com.example.myapplication.ui.main.fragment.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhotosBinding
import com.example.myapplication.ui.main.fragment.listPhotos.adapter.ListPhotosFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PhotosFragment : Fragment() {

    private lateinit var dataBinding: FragmentPhotosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_photos,
            container, false
        )

        return dataBinding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDataToViews()
    }

    @ExperimentalCoroutinesApi
    private fun addDataToViews() {
        dataBinding.viewPagerListItemCategoryPhoto.adapter = ListPhotosFragmentAdapter(
            requireActivity()
        )

        TabLayoutMediator(
            dataBinding.tabListItemCategoryPhoto,
            dataBinding.viewPagerListItemCategoryPhoto
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.for_you)
                }
                1 -> {
                    tab.text = getString(R.string.top_orientation)
                }
                2 -> {
                    tab.text = getString(R.string.categories)
                }

            }
        }.attach()
    }
}