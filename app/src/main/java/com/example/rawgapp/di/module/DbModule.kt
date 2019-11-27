package com.example.rawgapp.di.module

import android.app.Application
import com.example.rawgapp.data.local.db.GameRoomDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDb(application: Application): GameRoomDb{
        return GameRoomDb.getDatabase(application)
    }

    @Provides
    @Singleton
    fun provideGameDao(gameRoomDb: GameRoomDb) = gameRoomDb.gameDao()

}