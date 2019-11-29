package com.example.rawgapp.data.local.entity

import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import android.R


@Entity(tableName = BaseEntity.GAME_TABLE)
data class GameEntity(
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    var gameId: Int = 0,
    @NonNull
    @SerializedName("name")
    var name: String = "",
    @NonNull
    @SerializedName("released")
    var released: String = "",
    @SerializedName("rating")
    var rating:Double=0.0,
    @SerializedName("background_image")
    var imageUrl: String = ""
) : BaseEntity(){
    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.alert_dark_frame)
                .into(view)
        }
    }
}


