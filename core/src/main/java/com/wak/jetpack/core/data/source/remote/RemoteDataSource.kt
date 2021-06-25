package com.wak.jetpack.core.data.source.remote

import android.util.Log
import com.wak.jetpack.core.data.source.remote.response.DetailMovieResponse
import com.wak.jetpack.core.data.source.remote.response.DetailTVResponse
import com.wak.jetpack.core.data.source.remote.retrofit.ApiResponse
import com.wak.jetpack.core.data.source.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){
    companion object {
        private const val TAG = "remote_data_source"
    }

    //get Trending movie by using retrofit
    suspend fun loadListMovies(): Flow<ApiResponse<List<DetailMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTrendingMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            }
            catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }

        }.flowOn(Dispatchers.IO)

    }

    //get Trending TV by using retrofit
    suspend fun loadListTvShow(): Flow<ApiResponse<List<DetailTVResponse>>> {
        return flow {
            try {
                val response = apiService.getTrendingTV()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            }
            catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}


