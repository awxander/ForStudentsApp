package com.example.forstudents

import android.app.Application
import com.example.forstudents.di.AppComponent

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
    }
}