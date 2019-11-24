package com.testcase.catsapp.presentation.fragment.gallery.callback

import com.testcase.catsapp.domain.model.Picture

interface PictureViewCallback {
    fun onPictureFavouriteButtonClicked(picture: Picture)
    fun onPictureDownloadButtonClicked(picture: Picture)
}