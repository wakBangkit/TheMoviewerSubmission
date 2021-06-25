package com.wak.jetpack.core.domain.usecase

import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.domain.repository.IMoviewerRepository

class MoviewerInteractor(private val moviewerRepository: IMoviewerRepository) : MoviewerUseCase {
    override fun loadListMovies() = moviewerRepository.loadListMovies()
    override fun loadListFavoriteMovies() = moviewerRepository.loadListFavoriteMovies()
    override fun updateMovie(movie: Movie) = moviewerRepository.updateMovie(movie)


    override fun loadListTvShow() = moviewerRepository.loadListTvShow()
    override fun loadListFavoriteTvShow() = moviewerRepository.loadListFavoriteTvShow()
    override fun updateTvShow(tvShow: TvShow) = moviewerRepository.updateTvShow(tvShow)
}