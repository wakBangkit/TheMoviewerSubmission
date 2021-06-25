package com.wak.jetpack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    var listId: Int,
    var title: String,
    var year: String,
    var desc: String,
    var image: String,
    var poster: String,
    var stateFav: Boolean = false
) : Parcelable