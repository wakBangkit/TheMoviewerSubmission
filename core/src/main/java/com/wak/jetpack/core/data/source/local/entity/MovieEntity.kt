package com.wak.jetpack.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "table_movies")
@Parcelize
data class MovieEntity (
    @PrimaryKey
    var listId: Int,
    var title: String,
    var year: String,
    var desc: String,
    var image: String,
    var poster: String,
    var stateFav :Boolean = false
): Parcelable