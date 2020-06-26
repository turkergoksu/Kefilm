package me.turkergoksu.kefilm.model.toprated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * Created by turkergoksu on 14-Jun-20, 3:02 AM
 */

class TopRatedItemDataSourceFactory(val mutableLiveData: MutableLiveData<TopRatedItemDataSource>) :
    DataSource.Factory<Int, TopRatedMovieItem>() {

    private lateinit var topRatedItemDataSource: TopRatedItemDataSource

    override fun create(): DataSource<Int, TopRatedMovieItem> {
        topRatedItemDataSource = TopRatedItemDataSource()
        mutableLiveData.postValue(topRatedItemDataSource)
        return topRatedItemDataSource
    }

}