package com.example.cleanpexels.data.repository

import androidx.paging.PagingSource
import com.example.cleanpexels.data.api.PexelsApiService
import com.example.cleanpexels.data.model.PexelsPhoto

class PexelsCuratedPhotoPagingSource(
    val pexelsPhotoApiService: PexelsApiService
) : PagingSource<Int, PexelsPhoto>() {
    override suspend fun load(params: PagingSource.LoadParams<Int>
    ): PagingSource.LoadResult<Int, PexelsPhoto> {
        try {
            // Start refresh at page DEFAULT_INIT_KEY if undefined.
            val nextPage = params.key ?: DEFAULT_INIT_KEY
            val response = pexelsPhotoApiService.getCuratedPhotos(
                perPage = params.loadSize,
                page = nextPage,
            )

            return PagingSource.LoadResult.Page(
                data = response.photos,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.photos.isEmpty()) null else response.page + 1
            )
        } catch (e: Exception) {
            return PagingSource.LoadResult.Error(e)
        }
    }


    companion object {
        const val DEFAULT_INIT_KEY = 1
    }
}