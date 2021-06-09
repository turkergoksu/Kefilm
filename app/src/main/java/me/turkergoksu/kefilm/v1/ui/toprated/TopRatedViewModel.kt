package me.turkergoksu.kefilm.v1.ui.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.turkergoksu.kefilm.v1.model.toprated.TopRatedItemDataSource
import me.turkergoksu.kefilm.v1.model.toprated.TopRatedItemDataSourceFactory
import me.turkergoksu.kefilm.v1.model.toprated.TopRatedMovieItem
import java.util.concurrent.Executors

/**
 * Created by turkergoksu on 13-Apr-20, 1:23 AM
 */

class TopRatedViewModel : ViewModel() {

    private lateinit var topRatedItemDataSourceFactory: TopRatedItemDataSourceFactory
    private lateinit var dataSourceMutableLiveData: MutableLiveData<TopRatedItemDataSource>

    private lateinit var pagedListLiveData: LiveData<PagedList<TopRatedMovieItem>>

    fun getPagedListLiveData(): LiveData<PagedList<TopRatedMovieItem>> {
        fetchTopRatedMovies()
        return pagedListLiveData
    }

    private fun fetchTopRatedMovies() {
        topRatedItemDataSourceFactory = TopRatedItemDataSourceFactory(MutableLiveData())
        dataSourceMutableLiveData = topRatedItemDataSourceFactory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setPrefetchDistance(4) // Defines how far from the edge of loaded content an access must be to trigger further loading.
            .build()

        val executor = Executors.newFixedThreadPool(5)
        pagedListLiveData =
            LivePagedListBuilder(topRatedItemDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build()
    }
}