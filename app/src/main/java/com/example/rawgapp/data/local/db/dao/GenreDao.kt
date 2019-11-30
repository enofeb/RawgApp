package com.example.rawgapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.rawgapp.data.local.entity.BaseEntity
import com.example.rawgapp.data.local.entity.GenreEntity
import io.reactivex.Single

@Dao
interface GenreDao:BaseDao<GenreEntity>{
    @Query("SELECT * FROM  ${BaseEntity.GENRE_TABLE}")
    fun getAllGenres(): Single<List<GenreEntity>>
}