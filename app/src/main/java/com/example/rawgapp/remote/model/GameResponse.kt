package com.example.rawgapp.remote.model

import android.os.Parcelable
import com.example.rawgapp.data.entity.GameEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResponse(
    val results:List<GameEntity> = arrayListOf()
):Parcelable