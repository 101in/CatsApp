package com.testcase.catsapp.presentation.fragment.favourites.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.testcase.catsapp.R
import com.testcase.catsapp.domain.model.Picture

class FavouritePictureHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val favouritePictureView = itemView as AppCompatImageView

    fun bind(picture: Picture) {
        loadImage(picture.url)
    }

    fun unbind() {
        Glide.with(favouritePictureView).clear(favouritePictureView)
    }

    private fun loadImage(url: String) {
        Glide.with(favouritePictureView)
            .load(url)
            .placeholder(R.drawable.ic_image_placeholder_24dp)
            .centerCrop()
            .into(favouritePictureView)
    }

}