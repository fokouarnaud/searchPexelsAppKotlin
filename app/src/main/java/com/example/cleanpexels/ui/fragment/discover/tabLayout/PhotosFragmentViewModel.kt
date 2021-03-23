package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cleanpexels.data.api.PexelsApiAccess
import com.example.cleanpexels.data.model.PexelsPhoto
import com.example.cleanpexels.data.repository.PexelsCuratedPhotoPagingSource
import com.example.cleanpexels.data.repository.PexelsPhotoPagingSource
import kotlinx.coroutines.flow.Flow


class PhotosFragmentViewModel(application: Application) : ViewModel() {

    private val apiService = PexelsApiAccess.getApiService()

    private val _allPhotos: Flow<PagingData<PexelsPhoto>>
    val allPhotos
        get() = _allPhotos

    private val _newPhotos: Flow<PagingData<PexelsPhoto>>
    val newPhotos
        get() = _newPhotos

    init {
        _allPhotos = searchPhotos()
        _newPhotos = curatedPhotos()
    }

    private fun searchPhotos(
        query: String = "Ocean, Tigers, Pears",
        orientation: String = "landscape"
    ): Flow<PagingData<PexelsPhoto>> {
        return Pager(
            PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            PexelsPhotoPagingSource(apiService, query, orientation)
        }.flow.cachedIn(viewModelScope)
    }

    private fun curatedPhotos(): Flow<PagingData<PexelsPhoto>> {
        return Pager(
            PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            PexelsCuratedPhotoPagingSource(apiService)
        }.flow.cachedIn(viewModelScope)
    }


}