package com.wak.jetpack.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wak.jetpack.core.domain.usecase.MoviewerUseCase

class ListFavoriteViewModel  ( moviewerUseCase: MoviewerUseCase): ViewModel() {
    val getFavMovies = moviewerUseCase.loadListFavoriteMovies().asLiveData()
    val getFavTvShow = moviewerUseCase.loadListFavoriteTvShow().asLiveData()
}