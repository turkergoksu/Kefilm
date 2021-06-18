package me.turkergoksu.kefilm.now_playing.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import me.turkergoksu.kefilm.now_playing.data.NowPlayingMovieItem
import me.turkergoksu.kefilm.theme.KefilmTheme

/**
 * Created by turkergoksu on 18-Jun-21.
 */
@Preview
@Composable
@ExperimentalPagerApi
fun PreviewNowPlayingMovies() {
    val movies = listOf(
        NowPlayingMovieItem(
            id = 0,
            posterPath = "",
            title = "The Irishman"
        ),
        NowPlayingMovieItem(
            id = 0,
            posterPath = "",
            title = "The Irishman"
        )
    )
    KefilmTheme {
        NowPlayingMovies(data = movies)
    }
}

@Composable
@ExperimentalPagerApi
fun NowPlayingMovies(data: List<NowPlayingMovieItem>) {
    val pagerState = rememberPagerState(pageCount = data.size)
    HorizontalPager(state = pagerState) { page ->
        if (data.isNotEmpty())
            NowPlayingMovieItem(nowPlayingMovieItem = data[page])
    }
}