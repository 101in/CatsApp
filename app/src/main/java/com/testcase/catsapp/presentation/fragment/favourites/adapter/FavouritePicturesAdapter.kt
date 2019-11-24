package com.testcase.catsapp.presentation.fragment.favourites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testcase.catsapp.R
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.presentation.fragment.gallery.callback.PictureViewCallback

class FavouritePicturesAdapter : RecyclerView.Adapter<FavouritePictureHolder>() {

    private var pictures = ArrayList<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePictureHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favourite_picture_view, parent, false)
        return FavouritePictureHolder(view)
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    override fun onBindViewHolder(holder: FavouritePictureHolder, position: Int) {
        val picture = pictures[position]
        holder.bind(picture)
    }

    override fun onViewRecycled(holder: FavouritePictureHolder) {
        holder.unbind()
    }

    fun setPictures(newPictures: List<Picture>) {
        pictures = ArrayList(newPictures)
        notifyDataSetChanged()
    }
}