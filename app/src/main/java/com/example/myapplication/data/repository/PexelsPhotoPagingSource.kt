package com.example.myapplication.data.repository

import androidx.paging.PagingSource
import com.example.myapplication.data.api.PexelsApiService
import com.example.myapplication.data.model.PexelsPhoto

class PexelsPhotoPagingSource(
    val pexelsPhotoApiService: PexelsApiService,
   val  query :String,
    val orientation:String ,
) : PagingSource<Int, PexelsPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PexelsPhoto> {
        try {
            // Start refresh at page DEFAULT_INIT_KEY if undefined.
            val nextPage = params.key ?: DEFAULT_INIT_KEY
            val response = pexelsPhotoApiService.searchPhotos(
                query = query,
                orientation = orientation,
                perPage = params.loadSize,
                page = nextPage,
            )

            return LoadResult.Page(
                data = response.photos,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.photos.isEmpty()) null else response.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


    companion object{
        const val DEFAULT_INIT_KEY=1
    }
}