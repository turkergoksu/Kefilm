package me.turkergoksu.kefilm.common.navigation

/**
 * Created by turkergoksu on 21-Jun-21.
 */
sealed class Screen(val route: String) {
    object NowPlaying : Screen(route = "nowPlaying")
}