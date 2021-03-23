package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanpexels.R
import com.example.cleanpexels.databinding.FragmentDiscoverTabLayoutBinding
import kotlinx.coroutines.flow.collectLatest

/**
 * A simple [Fragment] subclass.
 * Use the [DiscoverTabLayoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscoverTabLayoutFragment : Fragment() {

    private lateinit var dataBinding: FragmentDiscoverTabLayoutBinding
    private var type: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getInt(NAME_CLE_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_discover_tab_layout,
            container, false
        )

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val photosFragmentViewModel = ViewModelProvider(
            requireActivity(),
            PhotosFragmentViewModelFactory(requireActivity().application)
        ).get(
            PhotosFragmentViewModel::class.java
        )


        val whatIsNewPhotosAdapter = PhotosAdapter()
        val browseAllPhotosAdapter = PhotosLargeAdapter()

        dataBinding.whatIsNewPhotosRecyclerview.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = whatIsNewPhotosAdapter.withLoadStateFooter(
                footer = PhotosLoadStateAdapter { whatIsNewPhotosAdapter.retry() }
            )
        }




       /* dataBinding.browseAllPhotosRecyclerview.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = browseAllPhotosAdapter.withLoadStateFooter(
                footer = PhotosLoadStateAdapter { browseAllPhotosAdapter.retry() }
            )
        }*/

        when (type) {
            TYPE_PHOTOS -> {
                activity?.let {
                    lifecycleScope.launchWhenResumed {
                        photosFragmentViewModel.newPhotos.collectLatest { pagingData ->
                            whatIsNewPhotosAdapter.submitData(pagingData)
                        }
                    }

                   /* lifecycleScope.launchWhenResumed {
                        photosFragmentViewModel.allPhotos.collectLatest { pagingData ->
                            browseAllPhotosAdapter.submitData(pagingData)
                        }
                    }*/
                }
            }
            TYPE_VIDEOS -> {
                activity?.let {
                    lifecycleScope.launchWhenResumed {
                        photosFragmentViewModel.newPhotos.collectLatest { pagingData ->
                            whatIsNewPhotosAdapter.submitData(pagingData)
                        }
                    }
                    /*lifecycleScope.launchWhenResumed {
                        photosFragmentViewModel.allPhotos.collectLatest { pagingData ->
                            browseAllPhotosAdapter.submitData(pagingData)
                        }
                    }*/
                }
            }
        }
    }

    companion object {
        const val NAME_CLE_TYPE: String = "CLE_TYPE"
        const val TYPE_PHOTOS = 0
        const val TYPE_VIDEOS = 1

        fun newInstance(type: Int): DiscoverTabLayoutFragment {
            val fragment = DiscoverTabLayoutFragment()
            val args = Bundle()
            args.putInt(NAME_CLE_TYPE, type)
            fragment.arguments = args
            return fragment

        }

    }
}