package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanpexels.R

/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverTabLayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscoverTabLayoutFragment : Fragment() {

    private var type: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getInt(NAME_CLE_TYPE)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_tab_layout, container, false)
    }

    companion object {
        const val NAME_CLE_TYPE: String = "CLE_TYPE"
        const val TYPE_PHOTOS = 0
        const val TYPE_VIDEOS = 1

        fun getNewInstance(type: Int): DiscoverTabLayoutFragment {
            val fragment = DiscoverTabLayoutFragment()
            val args = Bundle()
            args.putInt(NAME_CLE_TYPE, type)
            fragment.arguments = args
            return fragment

        }

    }
}