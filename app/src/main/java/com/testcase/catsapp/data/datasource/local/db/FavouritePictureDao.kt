package com.testcase.catsapp.data.datasource.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavouritePictureDao {

    @Insert
    fun makePictureAsFavourite(favouritePictureEntity: FavouritePictureEntity): Completable

    @Delete
    fun removePicturesFromFavourite(favouritePictureEntity: FavouritePictureEntity): Completable

    @Query("SELECT * FROM FavouritePictures")
    fun getFavouritesList(): Single<List<FavouritePictureEntity>>

}