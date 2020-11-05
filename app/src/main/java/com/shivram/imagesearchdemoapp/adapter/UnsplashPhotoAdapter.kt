package com.shivram.imagesearchdemoapp.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.shivram.imagesearchdemoapp.R
import com.shivram.imagesearchdemoapp.model.PhotoModel
import com.shivram.imagesearchdemoapp.databinding.ItemPhotoBinding

class UnsplashPhotoAdapter (private val listener:onItemClickListener):
    PagingDataAdapter<PhotoModel, UnsplashPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =

            ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

   inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition

                if (position!=RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item!=null){
                        listener.onItemClick(item)
                    }
                }

            }
        }
        
        fun bind(photo: PhotoModel) {
            binding.apply {
                Log.d(TAG, "bind: photo ${photo.urls.regular}")
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewUserName.text = photo.user.username
            }
        }
    }

    interface onItemClickListener{
        fun onItemClick(photo: PhotoModel)
    }
    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoModel>() {
            override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel) =
                oldItem == newItem
        }
    }
}