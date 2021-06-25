package com.wak.jetpack.core.domain.repository

import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviewerRepository {
    fun loadListMovies(): Flow<Resource<List<Movie>>>
    fun loadListFavoriteMovies(): Flow<List<Movie>>
    fun updateMovie(movie: Movie)


    fun loadListTvShow(): Flow<Resource<List<TvShow>>>
    fun loadListFavoriteTvShow(): Flow<List<TvShow>>
    fun updateTvShow(tvShow: TvShow)
}