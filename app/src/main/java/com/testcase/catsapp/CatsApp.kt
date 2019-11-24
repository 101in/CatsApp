package com.testcase.catsapp

import android.app.Application
import com.testcase.catsapp.di.component.ApplicationComponent
import com.testcase.catsapp.di.component.DaggerApplicationComponent

class CatsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .contextBind(this)
            .build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
            private set
    }
}