package me.turkergoksu.kefilm.upcoming.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.upcoming.domain.UpcomingUseCase
import javax.inject.Inject

/**
 * Created by turkergoksu on 09-Jun-21.
 */
@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val upcomingUseCase: UpcomingUseCase
) : ViewModel() {

    private val _upcomingMovies = MutableLiveData<UpcomingMovieListViewState>()
    val upcomingMovies: LiveData<UpcomingMovieListViewState> = _upcomingMovies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        fetchUpcomingMovies()
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            upcomingUseCase.fetchUpcomingMovies().collect {
                when (it) {
                    is Resource.Error -> {
                    } // FIXME: 11-Jun-21 show snackbar maybe
                    is Resource.Loading -> _loading.value = true
                    is Resource.Success -> _upcomingMovies.value =
                        UpcomingMovieListViewState(it.data)
                }
            }
        }
    }
}