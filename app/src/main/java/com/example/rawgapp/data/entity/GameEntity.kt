package com.example.rawgapp.data.entity

import com.google.gson.annotations.SerializedName

data class GameEntity(
    @SerializedName("id")
    var gameId:Int,
    var name:String,
    @SerializedName("released")
    var releaseDate:String,
    @SerializedName("background_image")
    var resourceCode:String
)