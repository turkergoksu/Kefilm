package me.turkergoksu.kefilm.search.data

/**
 * Created by turkergoksu on 16-Nov-21.
 */
data class SearchItem(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseYear: Int,
    val voteAverage: Double,
)