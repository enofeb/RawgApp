package com.example.rawgapp.remote.api

import com.example.rawgapp.remote.model.GameResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi{
    // ?
    @GET("games")
    fun fetchGameList(@Query("page") pageNo:Int):Single<GameResponse>
}