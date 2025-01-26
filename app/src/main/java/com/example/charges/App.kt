package com.example.charges

import android.app.Application
import com.example.charges.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(applicationModule)
        }
    }

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }
}