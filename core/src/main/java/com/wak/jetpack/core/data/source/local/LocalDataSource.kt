package com.wak.jetpack.core.data.source.local

import com.wak.jetpack.core.data.source.local.entity.MovieEntity
import com.wak.jetpack.core.data.source.local.entity.TvShowEntity
import com.wak.jetpack.core.data.source.local.room.MoviewerDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moviewerDao: MoviewerDao) {

    //Movie
    fun loadListMovies(): Flow<List<MovieEntity>> = moviewerDao.loadListMovies()
    fun loadListFavoriteMovies() : Flow<List<MovieEntity>> = moviewerDao.loadListFavoriteMovies()
    suspend fun insertMovies(movies: List<MovieEntity>) = moviewerDao.insertMovies(movies)
    fun updateMovie(movie: MovieEntity) = moviewerDao.updateMovie(movie)

    //TV Show
    fun loadListTvShow(): Flow<List<TvShowEntity>> = moviewerDao.loadListTvShow()
    fun loadListFavoriteTvShow(): Flow<List<TvShowEntity>> = moviewerDao.loadListFavoriteTvShow()
    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = moviewerDao.insertTvShow(tvShows)
    fun updateTvShow(tvShow: TvShowEntity) = moviewerDao.updateTvShow(tvShow)
}