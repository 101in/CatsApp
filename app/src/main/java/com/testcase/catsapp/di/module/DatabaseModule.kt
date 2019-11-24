package com.testcase.catsapp.di.module

import android.content.Context
import com.testcase.catsapp.data.datasource.local.db.FavouritePictureDao
import com.testcase.catsapp.data.datasource.local.db.FavouritePicturesDatabase
import com.testcase.catsapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @ApplicationScope
    @Provides
    @JvmStatic
    fun provideFavouritePicturesDao(context: Context): FavouritePictureDao {
        return FavouritePicturesDatabase.getDatabase(context).favouritePictureDao()
    }

}