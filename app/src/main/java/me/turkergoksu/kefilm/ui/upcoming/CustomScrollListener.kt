package me.turkergoksu.kefilm.ui.upcoming

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding
import me.turkergoksu.kefilm.model.upcoming.UpcomingMovieItem
import me.turkergoksu.kefilm.utils.ImageLoadingUtil

/**
 * Created by turkergoksu on 12-Apr-20, 12:01 AM
 */

class CustomScrollListener(
    private val context: Context,
    private val layoutManager: LinearLayoutManager,
    private val binding: FragmentUpcomingBinding
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
            ImageLoadingUtil.changeMainFragmentBackground(
                context,
                binding,
                upcomingMovieList[currentMovieItemIndex].posterPath
            )
        }
    }

    fun setUpcomingMovieList(upcomingMovieList: List<UpcomingMovieItem>) {
        this.upcomingMovieList = upcomingMovieList
    }
}