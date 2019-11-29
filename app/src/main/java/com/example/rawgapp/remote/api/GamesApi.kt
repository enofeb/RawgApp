package com.example.rawgapp.remote.api

import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.remote.model.GameResponse
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi{
    @GET("games?page_size=10")
    fun fetchGameList(@Query("page") pageNo:Int):Single<GameResponse>

    @GET("games/{gameId}")
    fun fetchGame(@Path("gameId") gameId:Int): Single<GameDetailEntity>
}