package com.testcase.catsapp.data.repository

import com.testcase.catsapp.data.datasource.local.LocalDataSource
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.domain.repository.FavouritePicturesRepository
import io.reactivex.Completable
import io.reactivex.Single

class FavouritePicturesRepositoryImpl(
    private val cacheDataSource: LocalDataSource
): FavouritePicturesRepository {

    override fun removePictureFromFavourite(picture: Picture): Completable {
        return cacheDataSource.removePictureFromFavourite(picture)
    }

    override fun getFavouritePictures(): Single<List<Picture>> {
        return cacheDataSource.getFavouritesPictures()
    }

    override fun markPictureAsFavourite(picture: Picture): Completable {
        return cacheDataSource.markPictureAsFavourite(picture)
    }
}