package com.example.cleanpexels.ui.fragment.discover

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cleanpexels.ui.fragment.discover.tabLayout.DiscoverTabLayoutFragment

class DiscoverFragmentAdapter  (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(
        fragmentActivity
    ) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                DiscoverTabLayoutFragment.getNewInstance(DiscoverTabLayoutFragment.TYPE_PHOTOS)
            }
            else -> {
                DiscoverTabLayoutFragment.getNewInstance(DiscoverTabLayoutFragment.TYPE_VIDEOS)
            }
        }
    }
}