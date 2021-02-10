package com.example.myapplication.ui.main.fragment.photos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.R
import com.example.myapplication.data.api.PexelsApiAccess
import com.example.myapplication.data.model.PexelsPhoto
import com.example.myapplication.data.model.PexelsPhotoCategory
import com.example.myapplication.data.repository.PexelsPhotoPagingSource
import com.example.myapplication.utils.DataGenerator
import com.example.myapplication.utils.Resource


class PhotosFragmentViewModel(application: Application) : ViewModel() {

    private val apiService = PexelsApiAccess.getApiService()
    var liveDataListPexelsPhotosCategory: LiveData<Resource<List<PexelsPhotoCategory>>>


    init {
        liveDataListPexelsPhotosCategory = liveData {
            emit(Resource.loading(null))
            try {
                emit(
                    Resource.success(
                        getListPhotosCategory()
                    )
                )
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        application.applicationContext.getString(
                            R.string.erreur_chargement
                        ), null
                    )
                )
            }
        }
    }

    fun searchPhotos(
        query: String = "nature",
        orientation: String = "landscape"
    ): LiveData<PagingData<PexelsPhoto>> {
        return Pager(
            PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            PexelsPhotoPagingSource(apiService, query, orientation)
        }.flow.cachedIn(viewModelScope).asLiveData()
    }

    fun getListPhotosCategory(): List<PexelsPhotoCategory> {
        val listCategory = DataGenerator.getListCategory()
        val listPexelsPhotosCategory: MutableList<PexelsPhotoCategory> = mutableListOf()

        listCategory.forEach { targetCagetory ->
            listPexelsPhotosCategory.add(
                PexelsPhotoCategory(
                    targetCagetory,
                    searchPhotos(query = targetCagetory)
                )
            )
        }
        return listPexelsPhotosCategory
    }


}