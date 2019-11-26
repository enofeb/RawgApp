package com.example.rawgapp.data.repository

import android.util.Log
import com.example.rawgapp.data.local.db.dao.GameDao
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.remote.api.GamesApi
import com.example.rawgapp.remote.model.GameResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val gamesApi: GamesApi,
    private val gameDao: GameDao
) {

    companion object {
        const val TAG = "GameRepository"
    }

    fun insertGames(gameList: List<GameEntity>): Single<List<GameEntity>> {
        return getRemoteGames().flatMap {
            gameDao.insertList(it.results).andThen(gameDao.getPageGames())
        }
        // return gameDao.insertList(gameList)
        //   .subscribeOn(Schedulers.io()).andThen(gameDao.getPageGames())
    }

    fun getRemoteGames(): Single<GameResponse> {
        return gamesApi.fetchGameList(1)
            .doAfterSuccess { Log.e(TAG, it.results.size.toString()) }
    }

    fun findGame(id: Int): Single<GameEntity> {
        return gameDao.findGame(id)
    }
}