package com.example.rawgapp.data.local.entity

import android.R
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso

@Entity(tableName = BaseEntity.GAME_DETAIL_TABLE)
data class GameDetailEntity(
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    var gameId: Int = 0,
    @NonNull
    @SerializedName("slug")
    var slug:String="",
    @NonNull
    @SerializedName("name")
    var name: String = "",
    @NonNull
    @SerializedName("description")
    var description:String="",
    @NonNull
    @SerializedName("rating")
    var rating:Double=0.0,
    @NonNull
    @SerializedName("released")
    var released: String = "",
    @SerializedName("playtime")
    var playTime: Int = 0,
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