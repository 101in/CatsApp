package com.testcase.catsapp.presentation.fragment.favourites

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.testcase.catsapp.domain.model.Picture

interface FavouritePicturesView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFavourites(pictures: List<Picture>)
}