package me.turkergoksu.kefilm.movie_detail.ui

import androidx.compose.ui.graphics.Color
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.ext.formatDefaultDate
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail
import me.turkergoksu.kefilm.theme.BadMovieRatingColor
import me.turkergoksu.kefilm.theme.GoodMovieRatingColor
import me.turkergoksu.kefilm.theme.MediocreMovieRatingColor

data class MovieDetailViewState(val movieDetail: MovieDetail) {

    fun budgetText() = "$%,d".format(movieDetail.budget)

    fun genreText() = buildString {
        movieDetail.genres.forEach {
            append(it.name)
            append(" ")
        }
    }

    fun overview() = movieDetail.overview

    fun posterUrl() = Constants.API_IMAGE_URL + movieDetail.posterPath

    fun releaseYear() = movieDetail.releaseDate.formatDefaultDate(MOVIE_DETAIL_DATE_FORMAT)

    fun runtimeText(): String {
        val hour = movieDetail.runtime / 60
        val minute = movieDetail.runtime % 60
        return "${hour}h ${minute}m"
    }

    fun title() = movieDetail.title

    fun voteAverage() = (movieDetail.voteAverage * 10).toInt()

    fun voteAverageText() = voteAverage().toString()

    fun voteAverageColor(): Color {
        val avg = voteAverage()
        return when {
            avg < 50 -> BadMovieRatingColor
            avg < 75 -> MediocreMovieRatingColor
            avg < 100 -> GoodMovieRatingColor
            else -> Color.Black
        }
    }

    companion object {
        const val MOVIE_DETAIL_DATE_FORMAT = "yyyy"
    }
}