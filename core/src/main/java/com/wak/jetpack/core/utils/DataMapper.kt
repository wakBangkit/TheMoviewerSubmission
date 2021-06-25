package com.wak.jetpack.core.utils

import com.wak.jetpack.core.data.source.local.entity.MovieEntity
import com.wak.jetpack.core.data.source.local.entity.TvShowEntity
import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow

object DataMapper {
    fun mapMovieEntitiesToDomain(input: List<MovieEntity>):List<Movie>{
        return input.map {
            Movie(
                it.listId,
                it.title,
                it.year,
                it.desc,
                it.image,
                it.poster,
                it.stateFav
            )
        }
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>):List<TvShow>{
        return input.map{
            TvShow(
                it.listId,
                it.title,
                it.year,
                it.desc,
                it.image,
                it.poster,
                it.stateFav
            )
        }
    }

    fun mapMovieDomainToEntity(input: Movie): MovieEntity{
        return MovieEntity(
            input.listId,
            input.title,
            input.year,
            input.desc,
            input.image,
            input.poster,
            input.stateFav
        )
    }

    fun mapTvShowDomainToEntity(input: TvShow):TvShowEntity{
        return TvShowEntity(
            input.listId,
            input.title,
            input.year,
            input.desc,
            input.image,
            input.poster,
            input.stateFav
        )
    }
}