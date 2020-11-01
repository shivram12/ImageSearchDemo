package com.shivram.imagesearchdemoapp.pagination

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import com.shivram.imagesearchdemoapp.model.PhotoModel
import com.shivram.imagesearchdemoapp.network.ApiService
import retrofit2.HttpException
import java.io.IOException


private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource ( private val apiService: ApiService,
                             private val query: String
) : PagingSource<Int, PhotoModel>() {

    //    this is hit to the api and do pagination
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoModel> {
//    first time this page will be null or 0, so we have to start with 1 page
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = apiService.searchPhotos(query, position, params.loadSize)
            val photos = response.results
            Log.d(TAG, "load: $photos")
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        }catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}
