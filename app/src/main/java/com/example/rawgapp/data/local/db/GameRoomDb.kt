package com.example.rawgapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rawgapp.data.local.db.dao.GameDao
import com.example.rawgapp.data.local.entity.GameEntity

@Database(entities = [GameEntity::class],version = 2,exportSchema = false)
abstract class GameRoomDb : RoomDatabase() {

    abstract fun gameDao():GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameRoomDb? = null

        fun getDatabase(context: Context): GameRoomDb {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDb::class.java,
                    "Game_Db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}