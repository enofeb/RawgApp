package com.example.rawgapp.data.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameEntity(
    @SerializedName("id")
    var gameId: Int= 0,
    @SerializedName("name")
    var name: String="",
    @SerializedName("released")
    var released: String="",
    @SerializedName("background_image")
    var imageUrl: String=""
):Parcelable