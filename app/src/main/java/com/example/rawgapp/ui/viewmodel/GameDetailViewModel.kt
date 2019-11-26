package com.example.rawgapp.ui.viewmodel

import com.example.rawgapp.data.repository.GameRepository
import com.example.rawgapp.ui.base.BaseViewModel
import javax.inject.Inject

class GameDetailViewModel @Inject constructor(private val gameRepository: GameRepository) :
    BaseViewModel() {

    fun loadGameInfo() {}
}