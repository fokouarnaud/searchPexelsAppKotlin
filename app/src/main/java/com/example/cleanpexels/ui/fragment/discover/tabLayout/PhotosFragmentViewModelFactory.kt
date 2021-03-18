package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotosFragmentViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosFragmentViewModel(mApplication) as T
    }
}