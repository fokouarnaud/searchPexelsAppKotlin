package com.example.myapplication.data.repository

import androidx.paging.PagingSource
import com.example.myapplication.data.api.PexelsApiService
import com.example.myapplication.data.model.PexelsPhoto

class PexelsPhotoPagingSource(
    val pexelsPhotoApiService: PexelsApiService
) : PagingSource<Int, PexelsPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PexelsPhoto> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: DEFAULT_INIT_KEY
            val response = pexelsPhotoApiService.searchPhotos(
                query = "nature",
                orientation = "landscape",
                perPage =  DEFAULT_PER_PAGE,
                page = nextPage,
            )

            return LoadResult.Page(
                data = response.photos,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


    companion object{
        const val DEFAULT_INIT_KEY=1
        const val DEFAULT_PER_PAGE=45
    }
}