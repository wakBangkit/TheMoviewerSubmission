package com.wak.jetpack.details

import androidx.lifecycle.ViewModel
import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.domain.usecase.MoviewerUseCase


class DetailViewModel(private val moviewerUseCase: MoviewerUseCase):ViewModel(){

    fun updateFavoriteMovie(_movie: Movie) {
        val movie = _movie
        movie.stateFav = !_movie.stateFav
        moviewerUseCase.updateMovie(movie)
    }

    fun updateFavoriteTvShow(_tvShow: TvShow) {
        val tvShow = _tvShow
        tvShow.stateFav = !_tvShow.stateFav
        moviewerUseCase.updateTvShow(tvShow)
    }


}