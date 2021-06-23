package me.turkergoksu.kefilm.popular.data

/**
 * Created by turkergoksu on 23-Jun-21.
 */
data class PopularMovieItem(
    val id: Int,
    val backdropPath: String,
    val title: String,
    val popularity: Int
)