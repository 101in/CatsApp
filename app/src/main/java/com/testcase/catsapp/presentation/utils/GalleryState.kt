package com.testcase.catsapp.presentation.utils

import com.testcase.catsapp.domain.model.Picture

sealed class GalleryState {
    object Loading : GalleryState()
    object ErrorLoading : GalleryState()
    data class Success(val pictures: List<Picture>): GalleryState()
}
