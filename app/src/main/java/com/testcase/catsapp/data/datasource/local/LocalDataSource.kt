package com.testcase.catsapp.data.datasource.local

import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSource {
    fun getFavouritesPictures(): Single<List<Picture>>
    fun markPictureAsFavourite(picture: Picture): Completable
    fun removePictureFromFavourite(picture: Picture): Completable
}