package me.turkergoksu.kefilm.movie_detail.ui

import androidx.compose.ui.graphics.Color
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.ext.formatDefaultDate
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail
import me.turkergoksu.kefilm.theme.BadMovieRatingColor
import me.turkergoksu.kefilm.theme.GoodMovieRatingColor
import me.turkergoksu.kefilm.theme.MediocreMovieRatingColor

data class MovieDetailViewState(val movieDetail: MovieDetail) {

    private val tba = "TBA"

    fun budgetText(): String {
        return if (movieDetail.budget != 0)
            "$%,d".format(movieDetail.budget)
        else
            tba
    }

    fun genreText() = buildString {
        movieDetail.genres.forEach {
            append(it.name)
            append(" ")
        }
    }

    fun overview() = movieDetail.overview

    fun posterUrl() = Constants.API_IMAGE_URL + movieDetail.posterPath

    fun releaseYear(): String {
        return if (movieDetail.releaseDate.isNotEmpty())
            movieDetail.releaseDate.formatDefaultDate(MOVIE_DETAIL_DATE_FORMAT)
        else
            tba
    }

    fun runtimeText(): String {
        return if (movieDetail.runtime != 0) {
            val hour = movieDetail.runtime / 60
            val minute = movieDetail.runtime % 60
            "${hour}h ${minute}m"
        } else
            tba
    }

    fun title() = movieDetail.title

    fun voteAverage() = (movieDetail.voteAverage * 10).toInt()

    fun voteAverageText(): String {
        return if (voteAverage() != 0)
            voteAverage().toString()
        else
            tba
    }

    fun voteAverageColor(): Color {
        val avg = voteAverage()
        return when {
            avg == 0 -> Color.Black
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