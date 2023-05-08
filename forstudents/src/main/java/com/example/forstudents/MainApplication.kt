package com.example.forstudents

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.forstudents.di.AppComponent
import com.example.forstudents.di.DaggerAppComponent

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)//выключил night mode
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> this.applicationContext.appComponent
    }