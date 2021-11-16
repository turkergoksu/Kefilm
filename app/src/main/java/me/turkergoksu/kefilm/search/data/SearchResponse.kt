package me.turkergoksu.kefilm.search.data

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 16-Nov-21.
 */
data class SearchResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val searchItemResponses: List<SearchItemResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

data class SearchItemResponse(
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
)