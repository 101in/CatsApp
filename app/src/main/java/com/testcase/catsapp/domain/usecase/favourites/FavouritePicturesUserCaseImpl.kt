package com.testcase.catsapp.domain.usecase.favourites

import com.testcase.catsapp.domain.repository.FavouritePicturesRepository

class FavouritePicturesUserCaseImpl(
    private val favouritePicturesRepository: FavouritePicturesRepository
) : FavouritePicturesUseCase {
    override fun getFavouritePictures() = favouritePicturesRepository.getFavouritePictures()
}