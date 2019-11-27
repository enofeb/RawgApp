package com.example.rawgapp.data.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.repository.GameRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class GameDataSource(
    private val gameRepository: GameRepository,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, GameEntity>() {

    val TAG: String get() = GameDataSource::class.java.simpleName
    val TOTALPAGE:Int=10

    private var page = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GameEntity>
    ) {
        compositeDisposable.add(
            gameRepository.getRemoteGames(page)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e(TAG,"loadBefore")
                    callback.onResult(it.results, null, page + 1)
                }, {
                    Log.e(TAG, it.message)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameEntity>) {
        compositeDisposable.add(
            gameRepository.getRemoteGames(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                   if (TOTALPAGE>=params.key){
                       Log.e(TAG,"loadAfter")
                       callback.onResult(it.results,params.key+1 )
                   }
                }, {
                    Log.e(TAG, it.message)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameEntity>) {
        compositeDisposable.add(
            gameRepository.getRemoteGames(page)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.results, null)
                }, {
                    Log.e(TAG, it.message)
                })
        )
    }
}