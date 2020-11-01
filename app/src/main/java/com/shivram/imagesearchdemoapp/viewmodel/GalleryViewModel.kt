package com.shivram.imagesearchdemoapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shivram.imagesearchdemoapp.repository.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor( private val repository: UnsplashRepository):ViewModel(){

}