package com.testcase.catsapp.presentation.fragment.gallery

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.presentation.utils.DownloadPictureState
import com.testcase.catsapp.presentation.utils.GalleryState

interface GalleryView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun processGalleryState(galleryState: GalleryState)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun processPartialLoadingError(show: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun togglePictureAsFavourite(position: Int, picture: Picture)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSaveProgressDialog(downloadPictureState: DownloadPictureState)
}