package me.turkergoksu.kefilm.movie_detail.data

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseYear: String,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)