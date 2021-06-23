package me.turkergoksu.kefilm.popular.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import me.turkergoksu.kefilm.common.navigation.Screen

/**
 * Created by turkergoksu on 23-Jun-21.
 */
@ExperimentalPagerApi
@Composable
fun PopularScreen(navController: NavController, viewModel: PopularViewModel) {
    val data = viewModel.popularMovieList.observeAsState().value?.popularMovieList ?: listOf()
    if (data.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(data.size) { index ->
                val movie = data[index]
                PopularMovieItem(item = movie) {
                    navController.navigate(Screen.MovieDetail.route(movieId = movie.id))
                }
            }
        }
    }
}