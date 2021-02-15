package com.example.myapplication.ui.main.fragment.listPhotos

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.main.fragment.photos.adapter.PhotosCategoryAdapter
import com.example.myapplication.ui.main.fragment.photos.base.PhotosFragmentViewModelFactory
import com.example.myapplication.ui.main.fragment.photos.viewmodel.PhotosFragmentViewModel
import com.example.myapplication.utils.Status

class ListPhotosFragment : Fragment() {

    private var type: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getInt(NAME_CLE_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list_item_category_photo, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val photosFragmentViewModel = ViewModelProvider(
            requireActivity(),
            PhotosFragmentViewModelFactory(requireActivity().application)
        ).get(
            PhotosFragmentViewModel::class.java
        )
        val recyclerviewCategoryPhoto =
            view.findViewById<RecyclerView>(R.id.recyclerview_category_photo)


        addDataToViews(photosFragmentViewModel, recyclerviewCategoryPhoto)
    }

    private fun addDataToViews(
        photosFragmentViewModel: PhotosFragmentViewModel,
        recyclerviewCategoryPhoto: RecyclerView
    ) {
        val loadingDialog = Dialog(requireContext())
        val photosCategoryAdapter = PhotosCategoryAdapter(lifecycle, viewLifecycleOwner)
        recyclerviewCategoryPhoto.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = photosCategoryAdapter
        }

        when (type) {
            TYPE_FOR_YOU -> {
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
            TYPE_TOP_ORIENTATION -> {
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
            TYPE_CATEGORIES -> {
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
        const val TYPE_FOR_YOU = 1
        const val TYPE_TOP_ORIENTATION = 2
        const val TYPE_CATEGORIES = 3

        fun getNewInstance(type: Int): ListPhotosFragment {
            val fragment = ListPhotosFragment()
            val args = Bundle()
            args.putInt(NAME_CLE_TYPE, type)
            fragment.arguments = args
            return fragment

        }

    }
}