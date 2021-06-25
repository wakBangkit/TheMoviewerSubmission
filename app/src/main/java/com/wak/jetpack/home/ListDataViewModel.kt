package com.wak.jetpack.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wak.jetpack.core.domain.usecase.MoviewerUseCase

class ListDataViewModel ( moviewerUseCase: MoviewerUseCase): ViewModel() {
   val getMovies = moviewerUseCase.loadListMovies().asLiveData()
    val getTvShow = moviewerUseCase.loadListTvShow().asLiveData()
}