package com.testcase.catsapp.di.module

import com.testcase.catsapp.data.local.LocalManager
import com.testcase.catsapp.data.local.LocalManagerImpl
import com.testcase.catsapp.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
object LocalManagerModule {

    @ApplicationScope
    @Provides
    @JvmStatic
    fun provideLocalManager(okHttpClient: OkHttpClient): LocalManager {
        return LocalManagerImpl(okHttpClient)
    }

}