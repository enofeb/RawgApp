package com.example.rawgapp.data.local.db

import android.widget.EditText
import androidx.databinding.InverseMethod
import com.example.rawgapp.data.local.entity.GenreEntity
import io.reactivex.Observable

object GenreConverter {
    @JvmStatic
    @InverseMethod("getGenresName")
    fun getGenresName(genres: List<GenreEntity>):String{
        val nameList= Observable.just(genres).flatMapIterable { it }
            .collect({ ArrayList<String>()},{ t1, t2 ->t1.add(t2.name)}).map { it }.blockingGet()

        val textWithFormat = nameList.toString().replace("[", "").replace("]", "")
        return textWithFormat
    }
}