package com.example.rawgapp.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.rawgapp.data.local.db.dao.GameDao
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.paging.PageListGameBoundaryCallback
import com.example.rawgapp.remote.api.GamesApi
import com.example.rawgapp.remote.model.GameResponse
import io.reactivex.*
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


    fun getRemoteGames(page: Int): Single<List<GameEntity>> {
        return gamesApi.fetchGameList(page)
            .map { it.results }
            .doAfterSuccess { Log.e(TAG, it.size.toString()) }
    }

    fun findGame(id: Int): Single<GameEntity> {
        return gameDao.findGame(id)
    }

    fun insertGame(gameList: List<GameEntity>){
        gameDao.insertList(gameList)
    }

    fun deleteAllGames(){
        gameDao.deleteAllGames()
    }

    fun getAllGames() : Observable<PagedList<GameEntity>> = RxPagedListBuilder(gameDao.getGamesSource(), 5)
        .setBoundaryCallback(PageListGameBoundaryCallback(this))
        .buildObservable()

}