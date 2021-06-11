package me.turkergoksu.kefilm.upcoming.domain

import me.turkergoksu.kefilm.upcoming.data.UpcomingMovieItem
import me.turkergoksu.kefilm.upcoming.data.remote.UpcomingMovieResponse
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class UpcomingMovieMapper @Inject constructor() {

    fun mapFromResponse(movieList: List<UpcomingMovieResponse?>?) = movieList?.map {
        mapFromResponse(it)
    } ?: listOf()

    fun mapFromResponse(movie: UpcomingMovieResponse?) =
        UpcomingMovieItem(
            id = movie?.id ?: throw NullPointerException("Upcoming movie id is null"),
            posterPath = movie.posterPath ?: "",
            title = movie.title ?: "",
            releaseDate = movie.releaseDate?.formatDate() ?: ""
        )

    private fun String.formatDate(): String {
        val date = SimpleDateFormat(MOVIE_DB_DATE_FORMAT, Locale.US).parse(this)
        return SimpleDateFormat(UPCOMING_MOVIE_DATE_FORMAT, Locale.US).format(date)
        // FIXME: 11-Jun-21 check date nullability later
    }

    companion object {
        const val MOVIE_DB_DATE_FORMAT = "yyyy-MM-dd"
        const val UPCOMING_MOVIE_DATE_FORMAT = "dd MMM yyyy"
    }
}