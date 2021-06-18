package me.turkergoksu.kefilm.now_playing.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.fragment.app.viewModels
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.core.BaseComposeFragment
import me.turkergoksu.kefilm.now_playing.ui.compose.NowPlayingMovies
import me.turkergoksu.kefilm.theme.KefilmTheme

/**
 * Created by turkergoksu on 09-Jun-21.
 */
@AndroidEntryPoint
class NowPlayingFragment : BaseComposeFragment<NowPlayingViewModel>() {

    override val viewModel: NowPlayingViewModel by viewModels()

    @Composable
    @ExperimentalPagerApi
    override fun Content() {
        KefilmTheme {
            viewModel.fetchNowPlayingMovies()
            val state = viewModel.nowPlayingMovies.observeAsState(
                initial = NowPlayingMovieListViewState(
                    nowPlayingMovieList = listOf()
                )
            )
            val movieList = state.value.nowPlayingMovieList
            NowPlayingMovies(data = movieList)
        }
    }

    companion object {
        fun newInstance() = NowPlayingFragment()
    }
}