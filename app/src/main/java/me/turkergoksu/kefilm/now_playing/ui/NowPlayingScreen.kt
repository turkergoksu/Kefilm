package me.turkergoksu.kefilm.now_playing.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import me.turkergoksu.kefilm.common.navigation.Screen

/**
 * Created by turkergoksu on 21-Jun-21.
 */
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun NowPlayingScreen(navController: NavController, viewModel: NowPlayingViewModel) {

    val data = viewModel.nowPlayingMovies.observeAsState().value?.nowPlayingMovieList ?: listOf()
    if (data.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = data.size)
        HorizontalPager(state = pagerState) { page ->
            val movie = data[page]
            NowPlayingMovieItem(nowPlayingMovieItem = movie) {
                navController.navigate(Screen.MovieDetail.route(movieId = movie.id))
            }
        }
    }
}