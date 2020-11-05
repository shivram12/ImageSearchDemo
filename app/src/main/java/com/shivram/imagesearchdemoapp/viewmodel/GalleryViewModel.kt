package com.shivram.imagesearchdemoapp.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shivram.imagesearchdemoapp.repository.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor( private val repository: UnsplashRepository):ViewModel(){

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

//    the switch map these executed whenever live data changes in the query method

    val photos = currentQuery.switchMap { queryString ->
        Log.d(TAG, "queryString: $queryString")
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "cats"
        private const val TAG = "viemodle"
    }
}