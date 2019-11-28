package com.example.rawgapp.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.base.BaseViewModel
import javax.inject.Inject


class GameListViewModel @Inject constructor(private val gameRepository: GameRepository) :
    BaseViewModel() {

    var pagedListGame = MutableLiveData<PagedList<GameEntity>>()

    fun loadGames() {
        compositeDisposable.add(
            gameRepository.getAllGames()
                .subscribe({
                    Log.e(TAG, it.toString())
                    pagedListGame.value = it
                },
                    { error -> Log.e(TAG, error.message) })
        )
    }


}