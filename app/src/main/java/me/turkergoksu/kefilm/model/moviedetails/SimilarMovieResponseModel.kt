package me.turkergoksu.kefilm.model.moviedetails

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 24-Jun-20, 6:22 PM
 */

data class SimilarMovieResponseModel(
    @SerializedName("results")
    val results: List<SimilarMovie>
)

data class SimilarMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)