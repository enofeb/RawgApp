package com.example.rawgapp.di.component

import android.app.Application
import com.example.rawgapp.di.module.ApiModule
import com.example.rawgapp.di.module.ViewModelModule
import com.example.rawgapp.ui.GameDetailActivity
import com.example.rawgapp.ui.GameListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class,ViewModelModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(gameListActivity: GameListActivity)
    fun inject(gameDetailActivity: GameDetailActivity)
}