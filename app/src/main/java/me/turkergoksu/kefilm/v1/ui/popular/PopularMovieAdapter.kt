package me.turkergoksu.kefilm.v1.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemPopularMovieBinding
import me.turkergoksu.kefilm.v1.Constants
import me.turkergoksu.kefilm.v1.model.popular.PopularMovieItem
import kotlin.random.Random

/**
 * Created by turkergoksu on 25-Jun-20, 8:16 PM
 */

class PopularMovieAdapter :
    PagedListAdapter<PopularMovieItem, PopularMovieAdapter.MovieItemViewHolder>(
        PopularMovieItem.CALLBACK
    ) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieItemViewHolder = MovieItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class MovieItemViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val set = ConstraintSet()

        fun bind(popularMovieItem: PopularMovieItem?) {
            // Set movie's poster image
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    popularMovieItem!!.posterPath
                )
            ).into(binding.imageViewMoviePoster)

            // Set random height
            val ratio = String.format(
                "%d:%d",
                Constants.POPULAR_MOVIE_ITEM_DEFAULT_WIDTH,
                getRandomHeight()
            )
            set.clone(binding.constraintLayout)
            set.setDimensionRatio(binding.imageViewMoviePoster.id, ratio)
            set.applyTo(binding.constraintLayout)

            // Set click listener
            binding.root.setOnClickListener {
                val args = Bundle()
                args.putInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY, popularMovieItem.id)

                Navigation.findNavController(it).navigate(R.id.movieDetailsFragment, args)
            }
        }

        private fun getRandomHeight(): Int {
            return Random.nextInt(200, 296)
        }

        companion object {
            fun create(parent: ViewGroup): MovieItemViewHolder {
                val binding = ItemPopularMovieBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return MovieItemViewHolder(binding)
            }
        }
    }
}