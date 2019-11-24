package com.testcase.catsapp.di.component

import com.testcase.catsapp.di.module.DataModule
import com.testcase.catsapp.di.module.DomainModule
import com.testcase.catsapp.di.scopes.PresenterScope
import com.testcase.catsapp.presentation.presenter.FavouritePicturesPresenter
import com.testcase.catsapp.presentation.presenter.GalleryPresenter
import dagger.Component

@PresenterScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [DomainModule::class, DataModule::class])
interface PresenterComponent {
    fun inject(galleryPresenter: GalleryPresenter)
    fun inject(favouritePicturesPresenter: FavouritePicturesPresenter)
}