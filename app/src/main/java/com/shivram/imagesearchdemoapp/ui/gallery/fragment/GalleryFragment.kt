package com.shivram.imagesearchdemoapp.ui.gallery.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.shivram.imagesearchdemoapp.R
import com.shivram.imagesearchdemoapp.adapter.UnsplashPhotoAdapter
import com.shivram.imagesearchdemoapp.databinding.FragmentGalleryBinding
import com.shivram.imagesearchdemoapp.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryBinding.bind(view)

        val adapter = UnsplashPhotoAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
        viewModel.photos.observe(viewLifecycleOwner){

            Log.d(TAG, "onViewCreated: ${it.toString()}")
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object{
        private const val TAG = "fragment"
    }
}