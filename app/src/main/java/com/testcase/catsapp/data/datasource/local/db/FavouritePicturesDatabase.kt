package com.testcase.catsapp.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavouritePictureEntity::class), version = 1, exportSchema = false)
abstract class FavouritePicturesDatabase : RoomDatabase() {

    abstract fun favouritePictureDao(): FavouritePictureDao

    companion object {
        @Volatile
        private var INSTANCE: FavouritePicturesDatabase? = null

        fun getDatabase(context: Context): FavouritePicturesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouritePicturesDatabase::class.java,
                    "pictures_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}