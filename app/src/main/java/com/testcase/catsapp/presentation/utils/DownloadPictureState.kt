package com.testcase.catsapp.presentation.utils

sealed class DownloadPictureState {
    data class Loading(val progress: Int): DownloadPictureState()
    object Error : DownloadPictureState()
    object Success : DownloadPictureState()
}