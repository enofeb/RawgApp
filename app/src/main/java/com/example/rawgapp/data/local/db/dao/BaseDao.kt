package com.example.rawgapp.data.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(games: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(games: T):Completable
}