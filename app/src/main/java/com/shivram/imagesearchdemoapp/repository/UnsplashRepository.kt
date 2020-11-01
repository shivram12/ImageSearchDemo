package com.shivram.imagesearchdemoapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.shivram.imagesearchdemoapp.network.ApiService
import com.shivram.imagesearchdemoapp.pagination.UnsplashPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository  @Inject constructor(private val apiService: ApiService) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(apiService, query) }
        ).liveData

}