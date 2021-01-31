package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class PexelsPhotoResponse(
    val page:Int,
    @SerializedName("per_page")
    val perPage:Int,
    @SerializedName("total_results")
    val totalResults:Long,
    val photos: List<PexelsPhoto>
)