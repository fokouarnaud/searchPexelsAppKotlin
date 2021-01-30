package com.example.myapplication.ui.main.fragment.videos.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.main.fragment.videos.viewmodel.VideosFragmentViewModel

class VideosFragmentViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideosFragmentViewModel(mApplication) as T
    }
}