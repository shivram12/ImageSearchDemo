package com.shivram.imagesearchdemoapp.network

import androidx.viewbinding.BuildConfig
import com.shivram.imagesearchdemoapp.model.PhotoResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService{
    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = "EpCNU6clTWJlPcqjz1-tCn2Pa0ehnNi5CetwiA5axQU"
    }

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int

    ):PhotoResult
}