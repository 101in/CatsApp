package com.testcase.catsapp.data.datasource.local.mapper

import com.testcase.catsapp.data.datasource.local.db.FavouritePictureEntity
import com.testcase.catsapp.domain.model.Picture

fun mapFavouritePicturesFromEntityToModel(pictures: List<FavouritePictureEntity>): List<Picture> {
    return pictures.map(::mapFavouritePictureFromEntityToModel)
}

fun mapFavouritePictureFromEntityToModel(picture: FavouritePictureEntity): Picture {
    return Picture(
        id = picture.id,
        url = picture.url,
        width = picture.width,
        height = picture.height,
        isFavourite = picture.isFavourite
    )
}

fun mapFavouritePictureFromModelToEntity(picture: Picture): FavouritePictureEntity {
    return FavouritePictureEntity(
        id = picture.id,
        url = picture.url,
        width = picture.width,
        height = picture.height,
        isFavourite = picture.isFavourite
    )
}