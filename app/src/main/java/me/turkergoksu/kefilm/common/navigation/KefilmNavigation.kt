package me.turkergoksu.kefilm.common.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import me.turkergoksu.kefilm.movie_detail.ui.MovieDetailScreen
import me.turkergoksu.kefilm.now_playing.ui.NowPlayingScreen

/**
 * Created by turkergoksu on 21-Jun-21.
 */

const val KEFILM_NAV_HOST_ROUTE = "kefilm-main-route"

@ExperimentalMaterialApi
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
        composable(
            Screen.MovieDetail.route,
            arguments = listOf(navArgument(Screen.MovieDetail.ARG_MOVIE_ID) {
                type = NavType.IntType
            })
        ) {
            val movieId = it.arguments?.getInt(Screen.MovieDetail.ARG_MOVIE_ID)
                ?: throw IllegalStateException("movieId cannot be null.")
            MovieDetailScreen(
                navController = navController,
                viewModel = hiltViewModel(),
                movieId = movieId
            )
        }
    }
}