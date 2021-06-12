package me.turkergoksu.kefilm.ext

import me.turkergoksu.kefilm.upcoming.domain.UpcomingMovieMapper
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by turkergoksu on 12-Jun-21.
 */
fun String.formatDefaultDate(newDateFormat: String): String {
    val date = SimpleDateFormat(UpcomingMovieMapper.MOVIE_DB_DATE_FORMAT, Locale.US).parse(this)
    return SimpleDateFormat(newDateFormat, Locale.US).format(date)
    // FIXME: 11-Jun-21 check date nullability later
}