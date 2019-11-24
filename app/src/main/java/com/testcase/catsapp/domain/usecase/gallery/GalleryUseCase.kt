package com.testcase.catsapp.domain.usecase.gallery

import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GalleryUseCase {
    fun fetchCats(page: Int, limit: Int): Single<List<Picture>>
    fun savePicture(picture: Picture): Flowable<Int>
    fun togglePictureAsFavourite(picture: Picture): Completable
}