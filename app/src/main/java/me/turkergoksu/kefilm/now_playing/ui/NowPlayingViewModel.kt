package me.turkergoksu.kefilm.now_playing.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.now_playing.domain.NowPlayingUseCase
import javax.inject.Inject

/**
 * Created by turkergoksu on 09-Jun-21.
 */
@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<NowPlayingMovieListViewState>()
    val nowPlayingMovies: LiveData<NowPlayingMovieListViewState> = _nowPlayingMovies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        fetchNowPlayingMovies()
    }

    fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            nowPlayingUseCase.fetchNowPlayingMovies().collect {
                when (it) {
                    is Resource.Error -> {
                    } // FIXME: 11-Jun-21 show snackbar maybe
                    is Resource.Loading -> _loading.value = true
                    is Resource.Success -> _nowPlayingMovies.value =
                        NowPlayingMovieListViewState(it.data)
                }
            }
        }
    }
}