package com.example.rawgapp.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.paging.GameDataSourceFactory
import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.base.BaseActivity
import com.example.rawgapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameListViewModel @Inject constructor(private val gameRepository: GameRepository) :
    BaseViewModel() {

    private var gameList: MutableLiveData<List<GameEntity>?> = MutableLiveData()

    lateinit var gamePagedList:LiveData<PagedList<GameEntity>>
    lateinit var gameDataSourceFactory:GameDataSourceFactory


    val gamePageList:LiveData<PagedList<GameEntity>> by lazy {
        getLiveGamePageList(compositeDisposable)
    }


    fun getLiveGamePageList(compositeDisposable: CompositeDisposable):LiveData<PagedList<GameEntity>>{

        gameDataSourceFactory= GameDataSourceFactory(gameRepository,compositeDisposable)

        val config=PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        gamePagedList= LivePagedListBuilder(gameDataSourceFactory,config).build()

        return gamePagedList

    }



  /* fun loadGames() {
        compositeDisposable.add(
            gameRepository.getRemoteGames(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, it.toString())
                    gameList.postValue(it.results)
                },
                    { error -> Log.e(TAG, error.message) })
        )
    }*/

    fun getGameList(): LiveData<List<GameEntity>?> {
        return gameList
    }

}