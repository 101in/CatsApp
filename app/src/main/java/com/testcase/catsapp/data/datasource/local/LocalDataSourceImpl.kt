package com.testcase.catsapp.data.datasource.local

import com.testcase.catsapp.data.datasource.local.db.FavouritePictureDao
import com.testcase.catsapp.data.datasource.local.mapper.mapFavouritePictureFromModelToEntity
import com.testcase.catsapp.data.datasource.local.mapper.mapFavouritePicturesFromEntityToModel
import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSourceImpl(private val favouritePicturesDaoFavourite: FavouritePictureDao) : LocalDataSource {

    override fun removePictureFromFavourite(picture: Picture): Completable {
        val favouritePictureEntity = mapFavouritePictureFromModelToEntity(picture)
        return favouritePicturesDaoFavourite.removePicturesFromFavourite(favouritePictureEntity)    }

    override fun getFavouritesPictures(): Single<List<Picture>> {
        return favouritePicturesDaoFavourite.getFavouritesList().map(::mapFavouritePicturesFromEntityToModel)
    }

    override fun markPictureAsFavourite(picture: Picture): Completable {
        val favouritePictureEntity = mapFavouritePictureFromModelToEntity(picture)
        return favouritePicturesDaoFavourite.makePictureAsFavourite(favouritePictureEntity)
    }

}