package me.turkergoksu.kefilm.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import me.turkergoksu.kefilm.movie_detail.ui.MovieDetailScreen
import me.turkergoksu.kefilm.now_playing.ui.NowPlayingScreen

/**
 * Created by turkergoksu on 21-Jun-21.
 */

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun KefilmNavigation(navHostController: NavHostController, bottomNavigationItems: List<Screen>) {
    Scaffold(
        bottomBar = {
            BottomBar(
                navHostController = navHostController,
                bottomNavigationItems = bottomNavigationItems
            )
        }
    ) { innerPadding ->
        Navigation(navHostController = navHostController, innerPadding = innerPadding)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController, bottomNavigationItems: List<Screen>) {
    BottomNavigation {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    if (screen.icon != null)
                        Icon(imageVector = screen.icon, contentDescription = null)
                },
                label = {
                    if (screen.resourceId != null)
                        Text(stringResource(screen.resourceId))
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NowPlaying.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.NowPlaying.route) {
            NowPlayingScreen(
                navController = navHostController,
                viewModel = hiltViewModel()
            )
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
                navController = navHostController,
                viewModel = hiltViewModel(),
                movieId = movieId
            )
        }
    }
}