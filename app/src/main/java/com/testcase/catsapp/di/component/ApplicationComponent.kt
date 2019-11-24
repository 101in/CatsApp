package com.testcase.catsapp.di.component

import android.content.Context
import com.testcase.catsapp.CatsApp
import com.testcase.catsapp.data.datasource.local.db.FavouritePictureDao
import com.testcase.catsapp.data.datasource.remote.api.CatsApi
import com.testcase.catsapp.data.local.LocalManager
import com.testcase.catsapp.di.module.DatabaseModule
import com.testcase.catsapp.di.module.LocalManagerModule
import com.testcase.catsapp.di.module.NetworkModule
import com.testcase.catsapp.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class,
                      LocalManagerModule::class,
                      DatabaseModule::class])
interface ApplicationComponent {
    fun getCatsApi(): CatsApi
    fun getLocalManager(): LocalManager
    fun getFavouritePicturesDao(): FavouritePictureDao
    fun inject(catsApp: CatsApp)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun contextBind(context: Context): Builder

    }
}