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
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhotosBinding
import com.example.myapplication.ui.main.fragment.photos.adapter.PhotosAdapter
import com.example.myapplication.ui.main.fragment.photos.adapter.PhotosLoadStateAdapter
import com.example.myapplication.ui.main.fragment.photos.base.PhotosFragmentViewModelFactory
import com.example.myapplication.ui.main.fragment.photos.viewmodel.PhotosFragmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PhotosFragment : Fragment() {

    private lateinit var dataBinding: FragmentPhotosBinding
    private lateinit var photosFragmentViewModel: PhotosFragmentViewModel
    private lateinit var photosAdapter: PhotosAdapter
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
        photosAdapter = PhotosAdapter()

        dataBinding.recyclerviewPhoto.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                1
            )
            adapter = photosAdapter.withLoadStateFooter(
                footer = PhotosLoadStateAdapter { photosAdapter.retry() }
            )

        }
    }




    @ExperimentalCoroutinesApi
    private fun addDataToViews() {

        lifecycleScope.launch {
            photosFragmentViewModel.pexelsPhotosLiveData.collectLatest {
                photosAdapter.submitData(it)
            }
        }

    }
}