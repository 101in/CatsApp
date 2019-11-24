package com.testcase.catsapp.presentation.fragment.gallery.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testcase.catsapp.R
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.presentation.fragment.gallery.list.holder.FooterHolder
import com.testcase.catsapp.presentation.fragment.gallery.list.holder.PictureHolder
import com.testcase.catsapp.presentation.fragment.gallery.callback.PartialLoadingCallback
import com.testcase.catsapp.presentation.fragment.gallery.callback.PartialReloadCallback
import com.testcase.catsapp.presentation.fragment.gallery.callback.PictureViewCallback

class GalleryAdapter(
    private val pictureViewCallback: PictureViewCallback,
    private val partialLoadingCallback: PartialLoadingCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pictures = ArrayList<Picture>()
    private var isPartialLoadingShown = false
    private var isPartialLoadingErrorShown = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == TYPE_PICTURE) {
            val view = inflater.inflate(R.layout.picture_view, parent, false)
            return PictureHolder(
                view,
                pictureViewCallback
            )
        } else {
            val view = inflater.inflate(R.layout.footer_view, parent, false)
            return FooterHolder(view, object : PartialReloadCallback {
                override fun onPartialReloadClicked() {
                    partialLoadingCallback.onNewPageRequested()
                }
            })
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position < pictures.size) {
            return TYPE_PICTURE
        }
        return TYPE_ERROR_LOADING
    }

    override fun getItemCount(): Int {
        val partialLoadingSize = if (isPartialLoadingShown) 1 else 0
        return pictures.size + partialLoadingSize
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < pictures.size) {
            val picture = pictures[position]
            (holder as PictureHolder).bind(picture)
        } else {
            val footerHolder = holder as FooterHolder
            if (isPartialLoadingErrorShown) {
                footerHolder.showError()
            } else {
                footerHolder.showLoading()
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is PictureHolder) {
            holder.unbind()
        }
    }

    fun setPictures(newPictures: List<Picture>) {
        pictures = ArrayList(newPictures)
        notifyDataSetChanged()
    }

    fun showPartialLoading() {
        isPartialLoadingShown = true
        notifyItemInserted(pictures.size)
    }

    fun processPartialLoadingError(show: Boolean) {
        isPartialLoadingShown = show
        isPartialLoadingErrorShown = show
        if (show) {
            notifyItemChanged(pictures.size)
        } else {
            notifyItemRemoved(pictures.size)
        }
    }

    fun togglePictureAsFavourite(position: Int, picture: Picture) {
        val pic = pictures[position]
        pic.isFavourite = picture.isFavourite
        notifyItemChanged(position)
    }

    fun isErrorLoadingItem(position: Int) = getItemViewType(position) == TYPE_ERROR_LOADING

    private companion object {
        private const val TYPE_PICTURE = 0
        private const val TYPE_ERROR_LOADING = 1
    }
}