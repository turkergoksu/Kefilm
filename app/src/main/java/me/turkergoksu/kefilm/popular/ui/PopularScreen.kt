package me.turkergoksu.kefilm.popular.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Created by turkergoksu on 23-Jun-21.
 */
@ExperimentalPagerApi
@Composable
fun PopularScreen(navController: NavController, viewModel: PopularViewModel) {
    val data = viewModel.popularMovieList.observeAsState().value?.popularMovieList ?: listOf()
    if (data.isNotEmpty()) {
        LazyColumn {
            items(data.size) { index ->
                PopularMovieItem(item = data[index]) {
                    // TODO: 23-Jun-21 navigate
                }
            }
        }
    }
}