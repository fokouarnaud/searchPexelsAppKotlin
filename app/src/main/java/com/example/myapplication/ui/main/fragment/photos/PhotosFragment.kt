package com.example.myapplication.ui.main.fragment.photos

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
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhotosBinding
import com.example.myapplication.ui.main.fragment.photos.adapter.PhotosCategoryAdapter
import com.example.myapplication.ui.main.fragment.photos.base.PhotosFragmentViewModelFactory
import com.example.myapplication.ui.main.fragment.photos.viewmodel.PhotosFragmentViewModel
import com.example.myapplication.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PhotosFragment : Fragment() {

    private lateinit var dataBinding: FragmentPhotosBinding
    private lateinit var photosFragmentViewModel: PhotosFragmentViewModel
    private lateinit var photosCategoryAdapter: PhotosCategoryAdapter
    private lateinit var loadingDialog: Dialog

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
        initialize()
        addDataToViews()
    }

    private fun initialize() {
        photosFragmentViewModel = ViewModelProvider(
            requireActivity(),
            PhotosFragmentViewModelFactory(requireActivity().application)
        ).get(
            PhotosFragmentViewModel::class.java
        )

        loadingDialog = Dialog(requireContext())
        photosCategoryAdapter = PhotosCategoryAdapter(lifecycle, viewLifecycleOwner)
        dataBinding.recyclerviewCategoryPhoto.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = photosCategoryAdapter
        }


    }


    @ExperimentalCoroutinesApi
    private fun addDataToViews() {
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