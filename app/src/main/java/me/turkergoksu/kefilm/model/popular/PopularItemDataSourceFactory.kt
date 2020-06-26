package me.turkergoksu.kefilm.model.popular

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * Created by turkergoksu on 25-Jun-20, 8:10 PM
 */

class PopularItemDataSourceFactory(val mutableLiveData: MutableLiveData<PopularItemDataSource>) :
        DataSource.Factory<Int, PopularMovieItem>() {

    private lateinit var popularItemDataSource: PopularItemDataSource

    override fun create(): DataSource<Int, PopularMovieItem> {
        popularItemDataSource = PopularItemDataSource()
        mutableLiveData.postValue(popularItemDataSource)
        return popularItemDataSource
    }
}