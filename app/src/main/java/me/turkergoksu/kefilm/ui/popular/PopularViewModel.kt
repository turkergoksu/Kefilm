package me.turkergoksu.kefilm.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.turkergoksu.kefilm.model.popular.PopularItemDataSource
import me.turkergoksu.kefilm.model.popular.PopularItemDataSourceFactory
import me.turkergoksu.kefilm.model.popular.PopularMovieItem
import me.turkergoksu.kefilm.model.toprated.TopRatedItemDataSourceFactory
import java.util.concurrent.Executors

/**
 * Created by turkergoksu on 25-Jun-20, 8:16 PM
 */

class PopularViewModel : ViewModel() {

    private lateinit var popularItemDataSourceFactory: PopularItemDataSourceFactory
    private lateinit var dataSourceMutableLiveData: MutableLiveData<PopularItemDataSource>

    private lateinit var pagedListLiveData: LiveData<PagedList<PopularMovieItem>>

    fun getPagedListLiveData(): LiveData<PagedList<PopularMovieItem>> {
        fetchPopularMovies()
        return pagedListLiveData
    }

    private fun fetchPopularMovies() {
        popularItemDataSourceFactory = PopularItemDataSourceFactory(MutableLiveData())
        dataSourceMutableLiveData = popularItemDataSourceFactory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()

        val executor = Executors.newFixedThreadPool(5)
        pagedListLiveData =
            LivePagedListBuilder(popularItemDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build()
    }
}