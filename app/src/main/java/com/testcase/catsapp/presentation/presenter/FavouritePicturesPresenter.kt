package com.testcase.catsapp.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.testcase.catsapp.domain.usecase.favourites.FavouritePicturesUseCase
import com.testcase.catsapp.presentation.fragment.favourites.FavouritePicturesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class FavouritePicturesPresenter : BasePresenter<FavouritePicturesView>() {

    @Inject
    lateinit var favouritePicturesUserCase: FavouritePicturesUseCase

    init {
        presenterComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        updatePictures()
    }

    fun updatePictures() {
        val disposable = favouritePicturesUserCase.getFavouritePictures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { favPics ->
                    viewState.showFavourites(favPics)
                },
                { exception ->
                    Log.e(LOG_TAG, "Action 'Get favourite pictures' is failed", exception)
                }
            )
        unsubscribeOnDestroy(disposable)
    }

    private companion object {
        private const val LOG_TAG = "FavouritePicturePresenter"
    }
}