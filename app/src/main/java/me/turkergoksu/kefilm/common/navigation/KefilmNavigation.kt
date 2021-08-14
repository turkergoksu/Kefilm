package me.turkergoksu.kefilm.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberNew
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.movie_detail.ui.MovieDetailScreen
import me.turkergoksu.kefilm.now_playing.ui.NowPlayingScreen
import me.turkergoksu.kefilm.popular.ui.PopularScreen

/**
 * Created by turkergoksu on 21-Jun-21.
 */

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun KefilmNavigation(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            val currentSelectedItem by navController.currentScreenAsState()

            KefilmBottomNavigation(
                selectedScreen = currentSelectedItem,
                onScreenSelected = { selected ->
                    navController.navigate(selected.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    }
                })
        }
    ) { innerPadding ->
        Navigation(navHostController = navController, innerPadding = innerPadding)
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.NowPlaying) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.NowPlaying.route } -> {
                    selectedItem.value = Screen.NowPlaying
                }
                destination.hierarchy.any { it.route == Screen.Popular.route } -> {
                    selectedItem.value = Screen.Popular
                }
                destination.hierarchy.any { it.route == Screen.MovieDetail.route } -> {
                    selectedItem.value = Screen.MovieDetail
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

@Composable
private fun KefilmBottomNavigation(
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit,
) {
    BottomNavigation(contentColor = contentColorFor(MaterialTheme.colors.surface)) {
        with(selectedScreen) {
            KefilmBottomNavigationItem(
                icon = Icons.Filled.FiberNew,
                label = stringResource(id = R.string.now_playing),
                selected = this == Screen.NowPlaying,
                onClick = { onScreenSelected(Screen.NowPlaying) }
            )

            KefilmBottomNavigationItem(
                icon = Icons.Filled.TrendingUp,
                label = stringResource(id = R.string.popular),
                selected = this == Screen.Popular,
                onClick = { onScreenSelected(Screen.Popular) }
            )
        }
    }
}

@Composable
private fun RowScope.KefilmBottomNavigationItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        icon = { Icon(imageVector = icon, tint = Color.White, contentDescription = null) },
        label = { Text(text = label, color = Color.White) },
        selected = selected,
        onClick = onClick,
    )
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun Navigation(navHostController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NowPlaying.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        addNowPlayingScreen(navController = navHostController)
        addPopularScreen(navController = navHostController)
        addMovieDetailScreen(navController = navHostController)
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addNowPlayingScreen(navController: NavController) {
    composable(Screen.NowPlaying.route) {
        NowPlayingScreen(navController = navController, viewModel = hiltViewModel())
    }
}

@ExperimentalPagerApi
private fun NavGraphBuilder.addPopularScreen(navController: NavController) {
    composable(Screen.Popular.route) {
        PopularScreen(navController = navController, viewModel = hiltViewModel())
    }
}

private fun NavGraphBuilder.addMovieDetailScreen(navController: NavController) {
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