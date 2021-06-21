package me.turkergoksu.kefilm.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import me.turkergoksu.kefilm.now_playing.ui.compose.NowPlayingScreen

/**
 * Created by turkergoksu on 21-Jun-21.
 */

const val KEFILM_NAV_HOST_ROUTE = "kefilm-main-route"

@ExperimentalPagerApi
@Composable
fun KefilmNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.NowPlaying.route,
        route = KEFILM_NAV_HOST_ROUTE
    ) {
        composable(Screen.NowPlaying.route) {
            NowPlayingScreen(navController = navController, viewModel = hiltViewModel())
        }
    }
}