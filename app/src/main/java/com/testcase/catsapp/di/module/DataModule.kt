package com.testcase.catsapp.di.module

import com.testcase.catsapp.data.datasource.local.LocalDataSource
import com.testcase.catsapp.data.datasource.local.LocalDataSourceImpl
import com.testcase.catsapp.data.datasource.local.db.FavouritePictureDao
import com.testcase.catsapp.data.datasource.remote.RemoteDataSource
import com.testcase.catsapp.data.datasource.remote.RemoteDataSourceImpl
import com.testcase.catsapp.data.datasource.remote.api.CatsApi
import com.testcase.catsapp.data.local.LocalManager
import com.testcase.catsapp.data.repository.FavouritePicturesRepositoryImpl
import com.testcase.catsapp.data.repository.GalleryRepositoryImpl
import com.testcase.catsapp.domain.repository.FavouritePicturesRepository
import com.testcase.catsapp.domain.repository.GalleryRepository
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    @JvmStatic
    fun provideLocalDataSource(favouritePictureDao: FavouritePictureDao): LocalDataSource {
        return LocalDataSourceImpl(favouritePictureDao)
    }

    @Provides
    @JvmStatic
    fun provideRemoteDataSource(catsApi: CatsApi): RemoteDataSource {
        return RemoteDataSourceImpl(catsApi)
    }

    @Provides
    @JvmStatic
    fun provideGalleryRepository(remoteDataSource: RemoteDataSource,
                                 localManager: LocalManager): GalleryRepository {
        return GalleryRepositoryImpl(remoteDataSource, localManager)
    }

    @Provides
    @JvmStatic
    fun provideFavouritePicturesRepository(cacheDataSource: LocalDataSource): FavouritePicturesRepository {
        return FavouritePicturesRepositoryImpl(cacheDataSource)
    }

}