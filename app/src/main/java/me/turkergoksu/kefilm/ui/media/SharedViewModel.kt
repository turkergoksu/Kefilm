package me.turkergoksu.kefilm.ui.media

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.model.moviedetails.Backdrop

/**
 * Created by turkergoksu on 11-Feb-21.
 */
class SharedViewModel : ViewModel() {

    val backdropList = MutableLiveData<List<Backdrop>>()
    val position = MutableLiveData<Int>()

    fun setBackdropList(backdropList: List<Backdrop>) {
        this.backdropList.value = backdropList
    }

    fun setPosition(position: Int) {
        this.position.value = position
    }
}