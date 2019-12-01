package com.example.rawgapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameDetailViewModel @Inject constructor(private val gameRepository: GameRepository) :
    BaseViewModel() {

    companion object {
        private const val TAG: String = "GameDetailViewModel"
    }

    private var game: MutableLiveData<GameDetailEntity> = MutableLiveData()

    private var checkInternet: MutableLiveData<Boolean> = MutableLiveData()

    fun loadGameInfo(gameId: Int) {
        compositeDisposable.add(
            gameRepository.getGameDetail(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    game.postValue(it)
                    Log.e(TAG, it.toString())
                }, {
                    //I know that error might have different cause but I just wanted to do that when the local db null,show the message
                        error ->
                    checkInternet.postValue(false)
                })
        )
    }

    fun getGameLD(): LiveData<GameDetailEntity> {
        return game
    }

    fun getCheck(): LiveData<Boolean> {
        return checkInternet
    }
}