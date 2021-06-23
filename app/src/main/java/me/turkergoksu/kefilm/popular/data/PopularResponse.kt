package me.turkergoksu.kefilm.popular.data

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 23-Jun-21.
 */
data class PopularResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val popularMovieResponseList: List<PopularMovieResponse>?,
)

data class PopularMovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("title")
    val title: String?,
)