package com.wak.jetpack.core.data.source

import com.wak.jetpack.core.data.source.local.LocalDataSource
import com.wak.jetpack.core.data.source.local.entity.MovieEntity
import com.wak.jetpack.core.data.source.local.entity.TvShowEntity
import com.wak.jetpack.core.data.source.remote.RemoteDataSource
import com.wak.jetpack.core.data.source.remote.response.DetailMovieResponse
import com.wak.jetpack.core.data.source.remote.response.DetailTVResponse
import com.wak.jetpack.core.data.source.remote.retrofit.ApiResponse
import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.domain.repository.IMoviewerRepository
import com.wak.jetpack.core.utils.AppExecutors
import com.wak.jetpack.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class MoviewerRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviewerRepository {

    //load for trending movie everyday
    override fun loadListMovies(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<DetailMovieResponse>>(appExecutors) {

            override fun loadFromDB(): Flow<List<Movie>> {
                //access local database
                return localDataSource.loadListMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            //access remote database
            override suspend fun createCall(): Flow<ApiResponse<List<DetailMovieResponse>>> =
                remoteDataSource.loadListMovies()

            //save remote to local database
            override suspend fun saveCallResult(data: List<DetailMovieResponse>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.title,
                        response.date,
                        response.overview,
                        response.backdrop,
                        response.poster
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertMovies(listMovie)
            }
        }.asFlow()
    }

    //load for trending TV everyday
    override fun loadListTvShow(): Flow<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResource<List<TvShow>, List<DetailTVResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<TvShow>> {
                //access local database
                return localDataSource.loadListTvShow().map{
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            //access remote database
            override suspend fun createCall(): Flow<ApiResponse<List<DetailTVResponse>>> =
                remoteDataSource.loadListTvShow()

            //save remote to local database
            override suspend fun saveCallResult(data: List<DetailTVResponse>) {
                val listTV = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tv = TvShowEntity(
                        response.id,
                        response.title,
                        response.date,
                        response.overview,
                        response.backdrop,
                        response.poster
                    )
                    listTV.add(tv)
                }
                localDataSource.insertTvShows(listTV)
            }
        }.asFlow()
    }


    override fun loadListFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.loadListFavoriteMovies().map{
            DataMapper.mapMovieEntitiesToDomain(it)
        }

    override fun loadListFavoriteTvShow(): Flow<List<TvShow>> =
        localDataSource.loadListFavoriteTvShow().map{
            DataMapper.mapTvShowEntitiesToDomain(it)
        }

    override fun updateMovie(movie: Movie) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.updateMovie(movieEntity) }

    }

    override fun updateTvShow(tvShow: TvShow) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.updateTvShow(tvShowEntity) }
    }
}