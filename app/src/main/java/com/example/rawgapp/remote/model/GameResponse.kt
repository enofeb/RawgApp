package com.example.rawgapp.remote.model

import com.example.rawgapp.data.local.entity.GameEntity

data class GameResponse(
    val results: List<GameEntity> = arrayListOf()
)