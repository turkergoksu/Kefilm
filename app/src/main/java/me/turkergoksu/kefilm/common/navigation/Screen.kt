package me.turkergoksu.kefilm.common.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberNew
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector
import me.turkergoksu.kefilm.R

/**
 * Created by turkergoksu on 21-Jun-21.
 */
sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int? = null,
    val icon: ImageVector? = null
) {
    // Bottom Nav
    object NowPlaying : Screen(
        route = "nowPlaying",
        resourceId = R.string.now_playing,
        icon = Icons.Filled.FiberNew
    )

    object Popular : Screen(
        route = "popular",
        resourceId = R.string.popular,
        icon = Icons.Filled.TrendingUp
    )

    object MovieDetail : Screen(route = "movieDetail/{movieId}") {
        fun route(movieId: Int) = "movieDetail/$movieId"

        const val ARG_MOVIE_ID = "movieId"
    }
}