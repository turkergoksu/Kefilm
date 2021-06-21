package me.turkergoksu.kefilm.movie_detail.data

data class MovieDetail(
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Double
)

data class Genre(
    val id: Int,
    val name: String
)