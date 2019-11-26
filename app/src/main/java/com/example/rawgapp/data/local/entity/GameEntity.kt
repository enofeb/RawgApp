package com.example.rawgapp.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = BaseEntity.GAME_TABLE)
data class GameEntity(
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    var gameId: Int= 0,
    @NonNull
    @SerializedName("name")
    var name: String="",
    @NonNull
    @SerializedName("released")
    var released: String="",
    @NonNull
    @SerializedName("background_image")
    var imageUrl: String=""
):BaseEntity()