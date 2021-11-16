package me.turkergoksu.kefilm.ext

import me.turkergoksu.kefilm.now_playing.domain.NowPlayingMovieMapper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by turkergoksu on 12-Jun-21.
 */
fun String.formatDefaultDate(newDateFormat: String): String? {
    return try {
        val date =
            SimpleDateFormat(NowPlayingMovieMapper.MOVIE_DB_DATE_FORMAT, Locale.US).parse(this)
        SimpleDateFormat(newDateFormat, Locale.US).format(date)
    } catch (exception: ParseException) {
        null
    }
    // FIXME: 11-Jun-21 check date nullability later
}