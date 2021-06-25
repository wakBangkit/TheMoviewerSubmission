package com.wak.jetpack.submission.di

import com.wak.jetpack.core.domain.usecase.MoviewerInteractor
import com.wak.jetpack.core.domain.usecase.MoviewerUseCase
import com.wak.jetpack.submission.details.DetailViewModel
import com.wak.jetpack.submission.home.ListDataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviewerUseCase> { MoviewerInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ListDataViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}