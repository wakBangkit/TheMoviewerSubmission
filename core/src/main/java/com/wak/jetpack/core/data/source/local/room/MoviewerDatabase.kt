package com.wak.jetpack.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wak.jetpack.core.data.source.local.entity.MovieEntity
import com.wak.jetpack.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MoviewerDatabase: RoomDatabase() {
    abstract fun moviewerDao() : MoviewerDao


}