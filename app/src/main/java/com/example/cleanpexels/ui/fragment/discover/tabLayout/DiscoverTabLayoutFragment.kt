package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanpexels.R
import com.example.cleanpexels.databinding.FragmentDiscoverTabLayoutBinding
import com.example.cleanpexels.utils.Status

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
        val loadingDialog = Dialog(requireContext())

        val photosFragmentViewModel = ViewModelProvider(
            requireActivity(),
            PhotosFragmentViewModelFactory(requireActivity().application)
        ).get(
            PhotosFragmentViewModel::class.java
        )
        val categoryPhotoRecyclerView =
            view.findViewById<RecyclerView>(R.id.category_photo_recyclerview)


        val photosCategoryAdapter = PhotosCategoryAdapter(lifecycle, viewLifecycleOwner)

        categoryPhotoRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = photosCategoryAdapter
        }

        when (type) {
            TYPE_PHOTOS -> {
                activity?.let {
                    lifecycleScope.launchWhenResumed {
                        photosFragmentViewModel.liveDataListPexelsPhotosCategory.observe(
                            viewLifecycleOwner, {
                                when (it.status) {
                                    Status.ERROR -> {
                                        loadingDialog.dismiss()
                                    }
                                    Status.LOADING -> {
                                        loadingDialog.setCancelable(false)
                                        loadingDialog.setContentView(R.layout.loading_dialog_layout)
                                        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                                        loadingDialog.show()
                                    }
                                    Status.SUCCESS -> {
                                        loadingDialog.dismiss()
                                        photosCategoryAdapter.submitList(it.data)
                                    }
                                }
                            }
                        )
                    }
                }
            }
            TYPE_VIDEOS -> {
                activity?.let {
                    lifecycleScope.launchWhenResumed {
                        photosFragmentViewModel.liveDataListPexelsPhotosCategory.observe(
                            viewLifecycleOwner, {
                                when (it.status) {
                                    Status.ERROR -> {
                                        loadingDialog.dismiss()
                                    }
                                    Status.LOADING -> {

                                        loadingDialog.setCancelable(false)
                                        loadingDialog.setContentView(R.layout.loading_dialog_layout)
                                        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                                        loadingDialog.show()
                                    }
                                    Status.SUCCESS -> {
                                        loadingDialog.dismiss()
                                        photosCategoryAdapter.submitList(it.data)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
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