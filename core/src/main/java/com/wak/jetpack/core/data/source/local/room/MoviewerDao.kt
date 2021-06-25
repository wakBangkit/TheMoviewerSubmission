package com.wak.jetpack.core.data.source.local.room

import androidx.room.*
import com.wak.jetpack.core.data.source.local.entity.MovieEntity
import com.wak.jetpack.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviewerDao {
    //Movies Dao
    @Query("SELECT * FROM table_movies")
    fun loadListMovies() : Flow<List<MovieEntity>> //load trending movie using retrofit

    @Query("SELECT * FROM table_movies WHERE stateFav = 1") //load favorite movie by filter stateFav is true
    fun loadListFavoriteMovies():  Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update // for update movie to favorite
    fun updateMovie(movie: MovieEntity)


    //TV Shows Dao
    @Query("SELECT * FROM table_tv_shows") //load trending tvshow using retrofit
    fun loadListTvShow() : Flow<List<TvShowEntity>>

    @Query("SELECT * FROM table_tv_shows WHERE stateFav = 1") //load favorite tvshow by filter stateFav is true
    fun loadListFavoriteTvShow(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShows: List<TvShowEntity>)

    @Update // for update tvshow to favorite
    fun updateTvShow(tvShow: TvShowEntity)

}