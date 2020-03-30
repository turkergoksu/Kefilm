package me.turkergoksu.kefilm.ui.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemUpcomingMovieBinding
import me.turkergoksu.kefilm.model.upcoming.UpcomingMovieItem

/**
 * Created by turkergoksu on 30-Mar-20, 8:19 PM
 */

class UpcomingMovieAdapter : RecyclerView.Adapter<UpcomingMovieAdapter.MovieItemViewHolder>() {

    private val upcomingMovieList = arrayListOf<UpcomingMovieItem>()

    fun setUpcomingMovieList(upcomingMovieList: List<UpcomingMovieItem>?) {
        if (upcomingMovieList != null) {
            this.upcomingMovieList.clear()
            this.upcomingMovieList.addAll(upcomingMovieList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder.create(parent)

    override fun getItemCount(): Int = upcomingMovieList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bind(upcomingMovieList[position])

    class MovieItemViewHolder(private val binding: ItemUpcomingMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(upcomingMovie: UpcomingMovieItem) {
            binding.imageViewMoviePoster.load(
                "%s%s".format(
                    Constants.apiImageUrl,
                    upcomingMovie.posterPath
                )
            )

            binding.textViewMovieTitle.text = upcomingMovie.title

            // TODO Format date yyyy-MM-dd to dd MMM yyyy (utils)
            binding.textViewMovieReleaseDate.text = upcomingMovie.releaseDate

            binding.textViewMovieOverview.text = upcomingMovie.overview
        }

        companion object {
            fun create(parent: ViewGroup): MovieItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemUpcomingMovieBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_upcoming_movie,
                    parent,
                    false
                )
                return MovieItemViewHolder(binding)
            }
        }
    }
}