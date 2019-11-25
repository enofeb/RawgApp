package com.example.rawgapp.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rawgapp.data.entity.GameEntity
import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.base.BaseActivity
import com.example.rawgapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameListViewModel @Inject constructor(private val gameRepository: GameRepository) :
    BaseViewModel() {

    private var gameList: MutableLiveData<List<GameEntity>?> = MutableLiveData()

    fun loadGames() {
        compositeDisposable.add(
            gameRepository.getRemoteGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it -> Log.e(TAG, it.toString())
                    gameList.postValue(it.results)
                },
                    { error -> Log.e(TAG, error.message) })
        )
    }

    fun getGameList(): LiveData<List<GameEntity>?> {
        return gameList
    }
}