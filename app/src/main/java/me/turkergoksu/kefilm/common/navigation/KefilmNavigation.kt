package me.turkergoksu.kefilm.common.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import me.turkergoksu.kefilm.search.ui.SearchScreen
import me.turkergoksu.kefilm.theme.BottomBackgroundColor

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
        Navigation(navHostController = navController)
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
                destination.hierarchy.any { it.route == Screen.Search.route } -> {
                    selectedItem.value = Screen.Search
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
    CustomKefilmBottomNavigation {
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

            KefilmBottomNavigationItem(
                icon = Icons.Filled.Search,
                label = stringResource(id = R.string.search),
                selected = this == Screen.Search,
                onClick = { onScreenSelected(Screen.Search) }
            )
        }
    }
}

@Composable
fun CustomKefilmBottomNavigation(
    content: @Composable RowScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp, vertical = 16.dp)
            .height(50.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        BottomNavigation(
            backgroundColor = BottomBackgroundColor,
            content = content
        )
    }
}

@Preview
@Composable
fun BottomNavPreview() {
    CustomKefilmBottomNavigation {
        KefilmBottomNavigationItem(
            icon = Icons.Filled.FiberNew,
            label = "Now Playing",
            selected = false
        ) {

        }
        KefilmBottomNavigationItem(
            icon = Icons.Filled.TrendingUp,
            label = "Popular",
            selected = true
        ) {

        }
        KefilmBottomNavigationItem(icon = Icons.Filled.Search, label = "Search", selected = false) {

        }
    }
}

@Composable
private fun KefilmBottomNavigationItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val color = if (selected) Color.DarkGray else Color.Transparent
    val iconTint = if (selected) Color.White else Color.Gray

    Row(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                onClick = onClick
            )
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = color,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            BottomNavigationItem(
                icon = { Icon(imageVector = icon, tint = iconTint, contentDescription = null) },
                selected = selected,
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onClick,
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun Navigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NowPlaying.route,
    ) {
        addNowPlayingScreen(navController = navHostController)
        addPopularScreen(navController = navHostController)
        addMovieDetailScreen()
        addSearchScreen(navController = navHostController)
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

private fun NavGraphBuilder.addMovieDetailScreen() {
    composable(
        Screen.MovieDetail.route,
        arguments = listOf(navArgument(Screen.MovieDetail.ARG_MOVIE_ID) {
            type = NavType.IntType
        })
    ) {
        val movieId = it.arguments?.getInt(Screen.MovieDetail.ARG_MOVIE_ID)
            ?: throw IllegalStateException("movieId cannot be null.")
        MovieDetailScreen(
            viewModel = hiltViewModel(),
            movieId = movieId
        )
    }
}

private fun NavGraphBuilder.addSearchScreen(navController: NavController) {
    composable(Screen.Search.route) {
        SearchScreen(navController = navController, viewModel = hiltViewModel())
    }
}