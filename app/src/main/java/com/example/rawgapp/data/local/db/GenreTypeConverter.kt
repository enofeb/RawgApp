package com.example.rawgapp.data.local.db

import androidx.room.TypeConverter
import com.example.rawgapp.data.local.entity.GenreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreTypeConverter {
    @TypeConverter
    fun fromOptionValuesList(levelValues: List<GenreEntity>?): String? {
        if (levelValues == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GenreEntity>>() {

        }.type
        return gson.toJson(levelValues, type)
    }

    @TypeConverter
    fun toOptionValuesList(levelValuesString: String?): List<GenreEntity>? {
        if (levelValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<GenreEntity>>() {

        }.type
        return gson.fromJson<List<GenreEntity>>(levelValuesString, type)
    }
}