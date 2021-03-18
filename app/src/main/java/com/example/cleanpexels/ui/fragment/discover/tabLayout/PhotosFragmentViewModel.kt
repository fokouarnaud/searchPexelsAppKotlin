package com.example.cleanpexels.ui.fragment.discover.tabLayout

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cleanpexels.R
import com.example.cleanpexels.data.api.PexelsApiAccess
import com.example.cleanpexels.data.model.PexelsPhoto
import com.example.cleanpexels.data.model.PexelsPhotoCategory
import com.example.cleanpexels.data.repository.PexelsPhotoPagingSource
import com.example.cleanpexels.utils.DataGenerator
import com.example.cleanpexels.utils.Resource


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
                            R.string.error_loading
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