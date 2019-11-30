package com.example.rawgapp.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = BaseEntity.GENRE_TABLE)
data class GenreEntity(
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    var id: Int = 0,
    @NonNull
    @SerializedName("name")
    var name: String = ""
) : BaseEntity()