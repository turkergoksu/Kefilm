package me.turkergoksu.kefilm.now_playing.domain

import me.turkergoksu.kefilm.now_playing.data.NowPlayingMovieItem
import me.turkergoksu.kefilm.now_playing.data.remote.NowPlayingMovieResponse
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class NowPlayingMovieMapper @Inject constructor() {

    fun mapFromResponse(movieList: List<NowPlayingMovieResponse?>?) = movieList?.map {
        mapFromResponse(it)
    } ?: listOf()

    fun mapFromResponse(movie: NowPlayingMovieResponse?) =
        NowPlayingMovieItem(
            id = movie?.id ?: throw NullPointerException("Upcoming movie id is null"),
            posterPath = movie.posterPath ?: "",
            title = movie.title ?: "",
        )

    companion object {
        const val MOVIE_DB_DATE_FORMAT = "yyyy-MM-dd"
    }
}