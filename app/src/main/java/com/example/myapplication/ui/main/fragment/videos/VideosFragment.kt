package com.example.myapplication.ui.main.fragment.videos

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentVideosBinding
import com.example.myapplication.ui.main.fragment.videos.adapter.VideosAdapter
import com.example.myapplication.ui.main.fragment.videos.base.VideosFragmentViewModelFactory
import com.example.myapplication.ui.main.fragment.videos.viewmodel.VideosFragmentViewModel
import com.example.myapplication.utils.Status


class VideosFragment : Fragment() {

    private lateinit var dataBinding: FragmentVideosBinding
    private lateinit var videosFragmentViewModel: VideosFragmentViewModel
    private lateinit var videosAdapter: VideosAdapter
    private lateinit var loadingDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_videos,
            container, false
        )

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        addDataToViews()
    }

    private fun initialize() {
        videosFragmentViewModel = ViewModelProvider(
            requireActivity(),
            VideosFragmentViewModelFactory(requireActivity().application)
        ).get(
            VideosFragmentViewModel::class.java
        )
        loadingDialog = Dialog(requireContext())
        videosAdapter = VideosAdapter()
    }

    private fun addDataToViews() {
        dataBinding.recyclerviewVideo.layoutManager = GridLayoutManager(
            requireContext(),
            2
        )
        dataBinding.recyclerviewVideo.adapter = videosAdapter

        lifecycleScope.launchWhenResumed {
            videosFragmentViewModel.pexelsVideosLiveData.observe(viewLifecycleOwner, {
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
                        videosAdapter.submitList(it.data)
                    }
                }
            })
        }
    }

}