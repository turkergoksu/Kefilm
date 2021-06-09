package me.turkergoksu.kefilm.v1.ui.upcoming

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.turkergoksu.kefilm.v1.model.upcoming.UpcomingMovieItem

/**
 * Created by turkergoksu on 12-Apr-20, 12:01 AM
 */

class CustomScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val callback: OnUpcomingFragmentEventListener
) : RecyclerView.OnScrollListener() {
    enum class Direction {
        LEFT, RIGHT
    }

    private lateinit var upcomingMovieList: List<UpcomingMovieItem>

    private lateinit var direction: Direction
    var currentMovieItemIndex: Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dx < 0) direction = Direction.LEFT
        else if (dx > 0) direction = Direction.RIGHT
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == 0) {
            if (direction == Direction.LEFT) currentMovieItemIndex =
                layoutManager.findFirstVisibleItemPosition()
            else if (direction == Direction.RIGHT) currentMovieItemIndex =
                layoutManager.findLastVisibleItemPosition()

            // Set background image for current item
            callback.onItemChange(upcomingMovieList[currentMovieItemIndex].posterPath)
        }
    }

    fun setUpcomingMovieList(upcomingMovieList: List<UpcomingMovieItem>) {
        this.upcomingMovieList = upcomingMovieList
    }
}