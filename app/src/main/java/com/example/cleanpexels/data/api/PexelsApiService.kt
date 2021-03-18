package com.example.cleanpexels.data.api


import com.example.cleanpexels.data.model.PexelsPhotoResponse
import com.example.cleanpexels.data.model.PexelsVideoResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsApiService {
    companion object {
        const val API_KEY = "563492ad6f91700001000001b0bb4b86a7674c84b8870e926b898f86"
        const val BASE_URL = "https://api.pexels.com/"
    }

    @Headers("Authorization: $API_KEY")
    @GET("v1/search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("orientation") orientation: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PexelsPhotoResponse

    @Headers("Authorization: $API_KEY")
    @GET("v1/curated")
    suspend fun getCuratedPhotos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PexelsPhotoResponse

    @Headers("Authorization: $API_KEY")
    @GET("videos/search")
    suspend fun searchVideos(
        @Query("query") query: String,
        @Query("orientation") orientation: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PexelsVideoResponse

    @Headers("Authorization: $API_KEY")
    @GET("videos/popular")
    suspend fun getCuratedVideos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PexelsVideoResponse
}