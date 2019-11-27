package com.example.rawgapp.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.repository.GameRepository
import io.reactivex.disposables.CompositeDisposable

class GameDataSourceFactory(
    private val gameRepository: GameRepository,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, GameEntity>() {

    val gameLiveDataSource=MutableLiveData<GameDataSource>()

    override fun create(): DataSource<Int, GameEntity> {
        val gameDataSource=GameDataSource(gameRepository,compositeDisposable)

        gameLiveDataSource.postValue(gameDataSource)

        return gameDataSource

    }
}