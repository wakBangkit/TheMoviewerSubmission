package com.wak.jetpack.core.di

import androidx.room.Room
import com.wak.jetpack.core.data.source.MoviewerRepository
import com.wak.jetpack.core.data.source.local.LocalDataSource
import com.wak.jetpack.core.data.source.local.room.MoviewerDatabase
import com.wak.jetpack.core.data.source.remote.RemoteDataSource
import com.wak.jetpack.core.data.source.remote.retrofit.ApiService
import com.wak.jetpack.core.domain.repository.IMoviewerRepository
import com.wak.jetpack.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviewerDatabase>().moviewerDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviewerDatabase::class.java, "moviewer.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviewerRepository> { MoviewerRepository(get(), get(), get()) }
}