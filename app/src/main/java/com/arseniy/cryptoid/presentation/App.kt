package com.arseniy.cryptoid.presentation

import android.app.Application
import com.arseniy.cryptoid.di.AppComponent
import com.arseniy.cryptoid.di.DaggerAppComponent
import com.facebook.stetho.Stetho

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        private var instance: App? = null
        @JvmStatic
        fun get(): App = instance!!
    }
}