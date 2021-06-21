package me.turkergoksu.kefilm.movie_detail.domain

import me.turkergoksu.kefilm.movie_detail.data.Genre
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail
import me.turkergoksu.kefilm.movie_detail.data.remote.MovieDetailResponse
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {

    fun mapFromResponse(response: MovieDetailResponse?) = MovieDetail(
        id = response?.id ?: throw NullPointerException("Movie detail id is null."),
        budget = response.budget ?: 0,
        genres = response.genres?.map { Genre(id = it.id ?: 0, name = it.name ?: "") } ?: listOf(),
        overview = response.overview ?: "",
        posterPath = response.posterPath ?: "",
        releaseDate = response.releaseDate ?: "",
        runtime = response.runtime ?: 0,
        title = response.title ?: "",
        voteAverage = response.voteAverage ?: 0.0
    )
}
