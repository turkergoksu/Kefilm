package me.turkergoksu.kefilm.movie_detail.domain

import me.turkergoksu.kefilm.ext.formatDefaultDate
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail
import me.turkergoksu.kefilm.movie_detail.data.remote.MovieDetailResponse

class MovieDetailMapper {

    fun mapFromResponse(response: MovieDetailResponse?) = MovieDetail(
        id = response?.id ?: throw NullPointerException("Movie detail id is null."),
        title = response.title ?: "",
        overview = response.overview ?: "",
        posterPath = response.posterPath ?: "",
        voteAverage = response.voteAverage ?: 0.0,
        voteCount = response.voteCount ?: 0,
        releaseYear = response.releaseDate?.formatDefaultDate(newDateFormat = MOVIE_DETAIL_DATE_FORMAT)
            ?: ""
    )

    companion object {
        const val MOVIE_DETAIL_DATE_FORMAT = "yyyy"
    }
}
