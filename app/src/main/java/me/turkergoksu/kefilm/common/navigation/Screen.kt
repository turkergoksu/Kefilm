package me.turkergoksu.kefilm.common.navigation

/**
 * Created by turkergoksu on 21-Jun-21.
 */
sealed class Screen(
    val route: String,
) {
    // Bottom Nav
    object NowPlaying : Screen(route = "nowPlaying")
    object Popular : Screen(route = "popular")
    object Search : Screen(route = "search")

    // Nav
    object MovieDetail : Screen(route = "movieDetail/{movieId}") {
        fun route(movieId: Int) = "movieDetail/$movieId"

        const val ARG_MOVIE_ID = "movieId"
    }
}