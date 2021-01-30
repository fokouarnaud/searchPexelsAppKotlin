package com.example.myapplication.ui.main.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.main.viewmodel.MainViewModel

class MainViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mApplication) as T
    }
}