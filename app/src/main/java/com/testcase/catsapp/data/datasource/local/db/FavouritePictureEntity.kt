package com.testcase.catsapp.data.datasource.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavouritePictures")
data class FavouritePictureEntity(
    @PrimaryKey val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    var isFavourite: Boolean
)