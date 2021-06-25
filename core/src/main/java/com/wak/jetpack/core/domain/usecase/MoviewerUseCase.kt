package com.wak.jetpack.core.domain.usecase

import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MoviewerUseCase {
    fun loadListMovies(): Flow<Resource<List<Movie>>>
    fun loadListFavoriteMovies(): Flow<List<Movie>>
    fun updateMovie(movie: Movie)


    fun loadListTvShow(): Flow<Resource<List<TvShow>>>
    fun loadListFavoriteTvShow(): Flow<List<TvShow>>
    fun updateTvShow(tvShow: TvShow)
}