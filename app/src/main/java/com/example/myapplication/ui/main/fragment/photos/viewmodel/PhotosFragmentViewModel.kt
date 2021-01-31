package com.example.myapplication.ui.main.fragment.photos.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.api.PexelsApiAccess
import com.example.myapplication.data.model.PexelsPhoto
import com.example.myapplication.data.repository.PexelsPhotoPagingSource
import kotlinx.coroutines.flow.Flow


class PhotosFragmentViewModel(application: Application) : ViewModel() {

    private val apiService = PexelsApiAccess.getApiService()
    val pexelsPhotosLiveData: Flow<PagingData<PexelsPhoto>> = Pager(PagingConfig(pageSize = 20)) {
        PexelsPhotoPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)

}