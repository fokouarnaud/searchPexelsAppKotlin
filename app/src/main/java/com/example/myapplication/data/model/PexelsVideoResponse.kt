package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class PexelsVideoResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_results")
    val totalResults: Long,
    val videos: List<PexelsVideo>
)