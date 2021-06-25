package com.wak.jetpack.favorite.di

import com.wak.jetpack.favorite.ListFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favoriteModule = module {
    viewModel { ListFavoriteViewModel(get()) }
}
