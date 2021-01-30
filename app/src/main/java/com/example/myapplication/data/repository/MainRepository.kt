package com.example.myapplication.data.repository

import android.app.Application
import com.example.myapplication.data.api.PexelsApiAccess
import com.example.myapplication.data.model.PexelsPhotoResponse
import com.example.myapplication.data.model.PexelsVideoResponse

class MainRepository(application: Application) {

    private val apiService = PexelsApiAccess.getApiService()

    companion object {
        private var instanceMainRepository: MainRepository? = null
        fun getInstance(application: Application): MainRepository {
            if (instanceMainRepository == null) {
                instanceMainRepository = MainRepository(application)
            }
            return instanceMainRepository!!
        }
    }

    suspend fun searchPhotos(
        query: String="nature",
        orientation: String = "landscape",
        perPage: Int = 30,
        page: Int = 1,
    ): PexelsPhotoResponse {
        return apiService.searchPhotos(
            query,
            orientation,
            perPage,
            page
        )
    }

    suspend fun searchVideos(
        query: String="nature",
        orientation: String = "landscape",
        perPage: Int = 30,
        page: Int = 1,
    ): PexelsVideoResponse {
        return apiService.searchVideos(
            query,
            orientation,
            perPage,
            page
        )
    }
}