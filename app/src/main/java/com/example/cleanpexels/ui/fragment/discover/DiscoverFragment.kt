package com.example.cleanpexels.ui.fragment.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cleanpexels.R
import com.example.cleanpexels.databinding.FragmentDiscoverBinding
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscoverFragment : Fragment() {

    private lateinit var dataBinding: FragmentDiscoverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_discover,
            container, false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.discoverViewPager.apply {
            adapter = DiscoverFragmentAdapter(
                requireActivity()
            )
            isUserInputEnabled = false
        }

        TabLayoutMediator(
            dataBinding.discoverTabLayout,
            dataBinding.discoverViewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.photos)
                }
                1 -> {
                    tab.text = getString(R.string.videos)
                }

            }
        }.attach()
    }
}