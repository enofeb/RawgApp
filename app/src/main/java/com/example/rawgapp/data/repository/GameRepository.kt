package com.example.rawgapp.data.repository

import android.util.Log
import com.example.rawgapp.remote.api.GamesApi
import com.example.rawgapp.remote.model.GameResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(private val gamesApi: GamesApi) {

    companion object {
        const val TAG = "GameRepository"
    }

     fun getRemoteGames():Single<GameResponse>{
        return gamesApi.fetchGameList(3)
            .doAfterSuccess{it-> Log.e(TAG,it.toString())}
    }
}