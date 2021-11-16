package me.turkergoksu.kefilm.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.search.data.SearchItem
import me.turkergoksu.kefilm.search.domain.SearchUseCase
import javax.inject.Inject

/**
 * Created by turkergoksu on 16-Nov-21.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    private val _searchItemList = MutableLiveData<List<SearchItem>>()
    val searchItemList: LiveData<List<SearchItem>> = _searchItemList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun search(query: String) = viewModelScope.launch {
        _query.value = query
        searchUseCase.fetchSearchResults(query).collect {
            when (it) {
                is Resource.Error -> {
                }
                is Resource.Loading -> _loading.value = true
                is Resource.Success -> {
                    _searchItemList.value = it.data
                    _loading.value = false
                }
            }
        }
    }
}