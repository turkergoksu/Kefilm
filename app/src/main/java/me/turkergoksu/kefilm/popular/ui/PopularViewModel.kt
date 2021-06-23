package me.turkergoksu.kefilm.popular.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.popular.domain.PopularUseCase
import javax.inject.Inject

/**
 * Created by turkergoksu on 23-Jun-21.
 */
@HiltViewModel
class PopularViewModel @Inject constructor(private val popularUseCase: PopularUseCase) :
    ViewModel() {

    private val _popularMovieList = MutableLiveData<PopularMovieListViewState>()
    val popularMovieList: LiveData<PopularMovieListViewState> = _popularMovieList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchPopularMovies() {
        viewModelScope.launch {
            popularUseCase.fetchPopularMovies().collect {
                when (it) {
                    is Resource.Error -> {
                    }
                    is Resource.Loading -> _loading.value = true
                    is Resource.Success -> {
                        _popularMovieList.value = PopularMovieListViewState(it.data)
                        _loading.value = false
                    }
                }
            }
        }
    }
}