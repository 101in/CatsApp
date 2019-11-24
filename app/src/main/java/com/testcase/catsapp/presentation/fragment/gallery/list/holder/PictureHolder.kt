package com.testcase.catsapp.presentation.fragment.gallery.list.holder

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.testcase.catsapp.R
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.presentation.fragment.gallery.callback.PictureViewCallback

class PictureHolder: RecyclerView.ViewHolder {

    private val pictureLoadingContainer: FrameLayout
    private val pictureLoadingProgressBar: ContentLoadingProgressBar
    private val pictureLoadingErrorContainer: LinearLayout
    private val pictureReload: Button

    private val pictureContainer: FrameLayout
    private val pictureView: AppCompatImageView
    private val favouritePictureView: AppCompatImageView
    private val downloadPictureView: AppCompatImageView

    private val pictureViewCallback: PictureViewCallback

    private var picture: Picture? = null

    constructor(
        itemView: View,
        pictureViewCallback: PictureViewCallback
    ) : super(itemView) {
        pictureLoadingContainer = itemView.findViewById(R.id.picture_loading_container)
        pictureLoadingProgressBar = itemView.findViewById(R.id.picture_loading_progress_bar)
        pictureLoadingErrorContainer = itemView.findViewById(R.id.picture_loading_error_container)
        pictureReload = itemView.findViewById(R.id.picture_loading_reload)

        pictureContainer = itemView.findViewById(R.id.picture_container)
        pictureView = itemView.findViewById(R.id.picture)
        favouritePictureView = itemView.findViewById(R.id.picture_favourite)
        downloadPictureView = itemView.findViewById(R.id.picture_download)

        this.pictureViewCallback = pictureViewCallback

        initCallbacks()
    }

    fun bind(picture: Picture) {
        this.picture = picture
        loadImage(picture.url)
        if (picture.isFavourite) {
            favouritePictureView.setImageResource(R.drawable.ic_favorite_24dp)
        } else {
            favouritePictureView.setImageResource(R.drawable.ic_unfavorite_24dp)
        }
    }

    fun unbind() {
        Glide.with(pictureView).clear(pictureView)
        picture = null
    }

    private fun loadImage(url: String) {
        showLoading()
        Glide.with(pictureView)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_image_placeholder_24dp)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    showErrorLoading()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    showImage()
                    return false
                }

            })
            .centerCrop()
            .into(pictureView)
    }

    private fun initCallbacks() {
        favouritePictureView.setOnClickListener {
            if (picture == null) {
                return@setOnClickListener
            }
            pictureViewCallback.onPictureFavouriteButtonClicked(picture!!)
        }
        downloadPictureView.setOnClickListener {
            if (picture == null) {
                return@setOnClickListener
            }
            pictureViewCallback.onPictureDownloadButtonClicked(picture!!)
        }
        pictureReload.setOnClickListener {
            if (picture == null) {
                return@setOnClickListener
            }
            loadImage(picture!!.url)
        }
    }

    private fun showImage() {
        pictureLoadingContainer.isVisible = false
        pictureLoadingProgressBar.hide()
    }

    private fun showLoading() {
        pictureLoadingContainer.isVisible = true
        pictureLoadingProgressBar.isVisible = true
        pictureLoadingErrorContainer.isVisible = false
        pictureLoadingProgressBar.show()
    }

    private fun showErrorLoading() {
        pictureLoadingContainer.isVisible = true
        pictureLoadingProgressBar.isVisible = false
        pictureLoadingErrorContainer.isVisible = true
        pictureLoadingProgressBar.hide()
    }
}