package com.example.my1app_melani_pilliza

import android.app.Application
import com.example.my1app_melani_pilliza.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin() {
            androidContext(this@MyApp)
            modules(
                appModule
            )
        }
    }
}