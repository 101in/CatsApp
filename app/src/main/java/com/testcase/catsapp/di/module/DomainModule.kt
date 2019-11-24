package com.testcase.catsapp.di.module

import com.testcase.catsapp.domain.repository.FavouritePicturesRepository
import com.testcase.catsapp.domain.repository.GalleryRepository
import com.testcase.catsapp.domain.usecase.favourites.FavouritePicturesUserCaseImpl
import com.testcase.catsapp.domain.usecase.favourites.FavouritePicturesUseCase
import com.testcase.catsapp.domain.usecase.gallery.GalleryUseCase
import com.testcase.catsapp.domain.usecase.gallery.GalleryUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    @JvmStatic
    fun provideGalleryUseCase(galleryRepository: GalleryRepository,
                              favouritePicturesRepository: FavouritePicturesRepository): GalleryUseCase {
        return GalleryUseCaseImpl(
            galleryRepository,
            favouritePicturesRepository
        )
    }

    @Provides
    @JvmStatic
    fun provideFavouriteUseCase(favouritePicturesRepository: FavouritePicturesRepository): FavouritePicturesUseCase {
        return FavouritePicturesUserCaseImpl(favouritePicturesRepository)
    }

}