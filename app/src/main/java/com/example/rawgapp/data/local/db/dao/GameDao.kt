package com.example.rawgapp.data.local.db.dao

import androidx.room.Dao
import com.example.rawgapp.data.local.entity.BaseEntity
import com.example.rawgapp.data.local.entity.GameEntity
import io.reactivex.Single
import androidx.room.Query

@Dao
interface GameDao:BaseDao<GameEntity> {

    @Query("SELECT * FROM  ${BaseEntity.GAME_TABLE}")
    fun getPageGames():Single<List<GameEntity>>

    @Query("SELECT * FROM  ${BaseEntity.GAME_TABLE} WHERE gameId=:mId")
    fun findGame(mId:Int):Single<GameEntity>
}