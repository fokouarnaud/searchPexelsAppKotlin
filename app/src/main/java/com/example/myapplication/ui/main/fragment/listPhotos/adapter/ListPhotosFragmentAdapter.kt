package com.example.myapplication.ui.main.fragment.listPhotos.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.main.fragment.listPhotos.ListPhotosFragment

class ListPhotosFragmentAdapter (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(
        fragmentActivity
    ) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ListPhotosFragment.getNewInstance(ListPhotosFragment.TYPE_FOR_YOU)
            }
            1 -> {
                ListPhotosFragment.getNewInstance(ListPhotosFragment.TYPE_TOP_ORIENTATION)
            }
            else -> {
                ListPhotosFragment.getNewInstance(ListPhotosFragment.TYPE_CATEGORIES)
            }
        }
    }
}