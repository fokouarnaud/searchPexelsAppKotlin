package com.example.myapplication.ui.main.fragment.photos.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.main.fragment.photos.viewmodel.PhotosFragmentViewModel

class PhotosFragmentViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosFragmentViewModel(mApplication) as T
    }
}