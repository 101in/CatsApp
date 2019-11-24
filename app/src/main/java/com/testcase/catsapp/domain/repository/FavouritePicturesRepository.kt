package com.testcase.catsapp.domain.repository

import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Completable
import io.reactivex.Single

interface FavouritePicturesRepository {
    fun getFavouritePictures(): Single<List<Picture>>
    fun markPictureAsFavourite(picture: Picture): Completable
    fun removePictureFromFavourite(picture: Picture): Completable
}