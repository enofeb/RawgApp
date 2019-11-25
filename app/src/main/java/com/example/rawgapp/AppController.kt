package com.example.rawgapp

import android.app.Application
import com.example.rawgapp.di.component.AppComponent
import com.example.rawgapp.di.component.DaggerAppComponent

class AppController : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {

        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}