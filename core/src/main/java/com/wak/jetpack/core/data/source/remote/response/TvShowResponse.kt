package com.wak.jetpack.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTVResponse(
    @field:SerializedName("results")
    val results: List<DetailTVResponse>
)

data class DetailTVResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("backdrop_path")
    val backdrop: String,

    @field:SerializedName("first_air_date")
    val date: String,
)