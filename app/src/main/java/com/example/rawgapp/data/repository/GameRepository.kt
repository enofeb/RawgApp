package com.example.rawgapp.data.repository

import android.util.Log
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.rawgapp.data.local.db.dao.GameDao
import com.example.rawgapp.data.local.db.dao.GameDetailDao
import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.paging.PageListGameBoundaryCallback
import com.example.rawgapp.remote.api.GamesApi
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.Maybe



@Singleton
class GameRepository @Inject constructor(
    private val gamesApi: GamesApi,
    private val gameDao: GameDao,
    private val gameDetailDao: GameDetailDao
) {

    companion object {
        const val TAG = "GameRepository"
    }

    fun getRemoteGames(page: Int): Single<List<GameEntity>> {
        return gamesApi.fetchGameList(page)
            .map { it.results }
            .doAfterSuccess { Log.e(TAG, it.size.toString()) }
    }

    fun getRemoteGameDetail(gameId: Int): Maybe<GameDetailEntity> {
        return gamesApi.fetchGame(gameId)
            .flatMapCompletable { gameDetailDao.insert(it) }
            .andThen(MaybeSource<GameDetailEntity>{ observer -> observer.onSuccess(gameDetailDao.findGameDetail(gameId)) })
            .subscribeOn(Schedulers.io())
    }

    fun getLocalGameDetail(gameId: Int):Maybe<GameDetailEntity>{
        val result=gameDetailDao.findGameDetail(gameId)
        return if (result != null) Maybe.just(gameDetailDao.findGameDetail(gameId)) else Maybe.empty()
    }

    fun getGameDetail(gameId: Int):Single<GameDetailEntity>{
        return Maybe.concat(getLocalGameDetail(gameId),getRemoteGameDetail(gameId))
            .firstOrError()
    }

    fun insertGames(gameList: List<GameEntity>) {
        gameDao.insertList(gameList)
    }

    fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    fun getAllGames(): Observable<PagedList<GameEntity>> =
        RxPagedListBuilder(gameDao.getGamesSource(), 5)
            .setBoundaryCallback(PageListGameBoundaryCallback(this))
            .buildObservable()

}