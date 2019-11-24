package com.testcase.catsapp.domain.usecase.favourites

import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Single

interface FavouritePicturesUseCase {
    fun getFavouritePictures(): Single<List<Picture>>
}