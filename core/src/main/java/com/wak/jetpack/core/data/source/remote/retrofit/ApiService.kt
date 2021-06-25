package com.wak.jetpack.core.data.source.remote.retrofit


import com.wak.jetpack.core.data.source.remote.response.ListMovieResponse
import com.wak.jetpack.core.data.source.remote.response.ListTVResponse
import com.wak.jetpack.core.utils.Constant.API_KEY
import retrofit2.http.GET


interface ApiService {

    @GET("trending/movie/day?api_key=$API_KEY")
    suspend fun getTrendingMovie(): ListMovieResponse

    @GET("trending/tv/day?api_key=$API_KEY")
    suspend fun getTrendingTV(): ListTVResponse

}