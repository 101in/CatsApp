package com.testcase.catsapp.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.domain.usecase.gallery.GalleryUseCase
import com.testcase.catsapp.domain.usecase.gallery.GalleryUseCaseImpl
import com.testcase.catsapp.presentation.fragment.gallery.GalleryView
import com.testcase.catsapp.presentation.fragment.gallery.list.PICTURE_PAGE_INITIAL_NUMBER
import com.testcase.catsapp.presentation.fragment.gallery.list.PICTURE_PAGE_LIMIT
import com.testcase.catsapp.presentation.utils.DownloadPictureState
import com.testcase.catsapp.presentation.utils.GalleryState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class GalleryPresenter: BasePresenter<GalleryView>() {

    @Inject
    lateinit var galleryUseCase: GalleryUseCase

    private val pictures = ArrayList<Picture>()
    private var currentPage = PICTURE_PAGE_INITIAL_NUMBER

    init {
        presenterComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fetchFreshData()
    }

    fun fetchFreshData() {
        fetchPage(PICTURE_PAGE_INITIAL_NUMBER, true)
    }

    fun fetchNextPage() {
        fetchPage(currentPage + 1, false)
    }

    private fun fetchPage(page: Int, refresh: Boolean) {
        val disposable = galleryUseCase.fetchCats(page, PICTURE_PAGE_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                if (pictures.isEmpty()) {
                    viewState.processGalleryState(GalleryState.Loading)
                }
            }
            .doOnSuccess {
                if (refresh) pictures.clear()
            }
            .subscribe(
                { pics ->
                    currentPage = page
                    pictures.addAll(pics)
                    viewState.processGalleryState(GalleryState.Success(pictures))
                    viewState.processPartialLoadingError(show = false)
                },
                { exception ->
                    if (pictures.isEmpty()) {
                        viewState.processGalleryState(GalleryState.ErrorLoading)
                    } else {
                        viewState.processPartialLoadingError(show = true)
                    }
                    Log.e(LOG_TAG, "Action 'fetchPage with arguments($page, $refresh)' is failed", exception)
                }
            )
        unsubscribeOnDestroy(disposable)
    }

    fun togglePictureAsFavourite(picture: Picture) {
        val disposable = galleryUseCase.togglePictureAsFavourite(picture)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { localTogglePictureAsFavourite(picture) },
                { exception -> Log.e(LOG_TAG, "Action 'Make picture (${picture}) as favourite' is failed", exception) }
            )
        unsubscribeOnDestroy(disposable)
    }

    fun downloadPicture(picture: Picture) {
        val disposable = galleryUseCase.savePicture(picture)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { progress ->
                    viewState.showSaveProgressDialog(DownloadPictureState.Loading(progress)) },
                { exception ->
                    viewState.showSaveProgressDialog(DownloadPictureState.Error)
                    Log.e(LOG_TAG, "Action 'Download picture (${picture})' is failed", exception)
                },
                { viewState.showSaveProgressDialog(DownloadPictureState.Success) }
            )
        unsubscribeOnDestroy(disposable)
    }

    private fun localTogglePictureAsFavourite(picture: Picture) {
        pictures.forEachIndexed { position, pic ->
            if (pic.id == picture.id) {
                pic.isFavourite = !pic.isFavourite
                viewState.togglePictureAsFavourite(position, pic)
                return@forEachIndexed
            }
        }
    }

    private companion object {
        private const val LOG_TAG = "GalleryPresenter"
    }
}