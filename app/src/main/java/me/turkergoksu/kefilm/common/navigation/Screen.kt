package me.turkergoksu.kefilm.common.navigation

/**
 * Created by turkergoksu on 21-Jun-21.
 */
sealed class Screen(val route: String) {
    object NowPlaying : Screen(route = "nowPlaying")
    object MovieDetail : Screen(route = "movieDetail/{movieId}") {
        fun route(movieId: Int) = "movieDetail/$movieId"

        const val ARG_MOVIE_ID = "movieId"
    }
}