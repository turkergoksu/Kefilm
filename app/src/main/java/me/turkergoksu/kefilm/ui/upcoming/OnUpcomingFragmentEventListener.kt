package me.turkergoksu.kefilm.ui.upcoming

/**
 * Created by turkergoksu on 16-Apr-20, 2:12 PM
 */

interface OnUpcomingFragmentEventListener {
    fun onItemChange(posterPath: String)
    fun onUpcomingFragmentStop()
}