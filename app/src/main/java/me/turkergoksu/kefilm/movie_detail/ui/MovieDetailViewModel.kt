package me.turkergoksu.kefilm.movie_detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail
import me.turkergoksu.kefilm.movie_detail.domain.MovieDetailUseCase
import javax.inject.Inject

/**
 * Created by turkergoksu on 12-Jun-21.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase,
) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailViewState>()
    val movieDetail: LiveData<MovieDetailViewState> = _movieDetail

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            useCase.fetchMovieDetail(movieId).collect {
                when (it) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> _loading.value = true
                    is Resource.Success -> onMovieDetailSuccess(it.data)
                }
            }
        }
    }

    private fun onMovieDetailSuccess(data: MovieDetail) {
        _movieDetail.value = MovieDetailViewState(data)

        _loading.value = false
    }
}