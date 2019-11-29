package com.example.rawgapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rawgapp.data.local.entity.GameDetailEntity
import com.example.rawgapp.data.local.entity.GameEntity
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

    fun loadGameInfo(gameId: Int) {
        compositeDisposable.add(
            gameRepository.getGameDetail(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    game.postValue(it)
                    Log.e(TAG, it.toString())
                }, { error -> Log.e(TAG, error.message, error) })
        )
    }

    fun getGameLD(): LiveData<GameDetailEntity> {
        return game
    }
}