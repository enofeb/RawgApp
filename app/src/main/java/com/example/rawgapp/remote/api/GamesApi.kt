package com.example.rawgapp.remote.api

import com.example.rawgapp.remote.model.GameResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi{
    @GET("games?page_size=10")
    fun fetchGameList(@Query("page") pageNo:Int):Single<GameResponse>
}