package com.example.rawgapp.data.paging

import android.util.Log
import androidx.paging.PagedList
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.data.repository.GameRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PageListGameBoundaryCallback(private val gameRepository: GameRepository) :
    PagedList.BoundaryCallback<GameEntity>() {

    companion object {
        private const val TAG: String = "PageListGameBoundary"
    }

    internal val compositeDisposable = CompositeDisposable()

    private var isRequestRunning = false
    private var initialPage = 1


    private fun getAndSaveGames() {
        if (isRequestRunning) return

        isRequestRunning = true

        compositeDisposable.add(gameRepository.getRemoteGames(initialPage)
            .doOnSuccess {
                if (initialPage != 5) {
                    gameRepository.insertGames(it)
                    Log.e(TAG, "Inserted: ${it}")
                } else {
                    Log.e(TAG, "No Inserted")
                }
                initialPage++
                Log.e(TAG, initialPage.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .ignoreElement()
            .doFinally { isRequestRunning = false }
            .subscribe({ Log.e(TAG, "It is Done") }, { it.printStackTrace() })
        )


    }

    override fun onZeroItemsLoaded() {
        Log.e(TAG, "ONZERO")
        getAndSaveGames()
    }

    override fun onItemAtEndLoaded(itemAtEnd: GameEntity) {
        Log.e(TAG, "ONLOAD")
        getAndSaveGames()
    }
}