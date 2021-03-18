package com.example.cleanpexels.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagingData


data class PexelsPhotoCategory (
    val name:String,
    val liveDataPagingDataPexelsPhoto: LiveData<PagingData<PexelsPhoto>>
)