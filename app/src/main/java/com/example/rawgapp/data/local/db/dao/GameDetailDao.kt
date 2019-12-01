package com.example.rawgapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.rawgapp.data.local.entity.BaseEntity
import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.data.local.entity.GameEntity

@Dao
interface GameDetailDao : BaseDao<GameDetailEntity> {

    @Query("SELECT * FROM  ${BaseEntity.GAME_DETAIL_TABLE}")
    fun getGameDetails(): List<GameEntity>

    @Query("SELECT * FROM  ${BaseEntity.GAME_DETAIL_TABLE} WHERE gameId=:mId")
    fun findGameDetail(mId: Int): GameDetailEntity

    @Query("DELETE FROM ${BaseEntity.GAME_DETAIL_TABLE}")
    fun deleteAllGames()
}