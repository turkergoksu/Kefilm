package me.turkergoksu.kefilm.v1.ui.upcoming

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemUpcomingMovieBinding
import me.turkergoksu.kefilm.v1.Constants
import me.turkergoksu.kefilm.v1.model.upcoming.UpcomingMovieItem
import me.turkergoksu.kefilm.v1.utils.StringUtil

/**
 * Created by turkergoksu on 30-Mar-20, 8:19 PM
 */

class UpcomingMovieAdapter(private val upcomingMovieList: List<UpcomingMovieItem>) :
    RecyclerView.Adapter<UpcomingMovieAdapter.MovieItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder.create(parent)

    override fun getItemCount(): Int = upcomingMovieList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bind(upcomingMovieList[position])

    class MovieItemViewHolder(private val binding: ItemUpcomingMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(upcomingMovie: UpcomingMovieItem) {
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    upcomingMovie.posterPath
                )
            ).apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        Constants.UPCOMING_MOVIE_ITEM_CORNER_RADIUS,
                        0
                    )
                )
            ).into(binding.imageViewMoviePoster)

            binding.textViewMovieTitle.text = upcomingMovie.title

            binding.textViewMovieReleaseDate.text = StringUtil.formatDate(
                upcomingMovie.releaseDate,
                Constants.MOVIE_DB_DATE_FORMAT,
                Constants.UPCOMING_MOVIE_DATE_FORMAT
            )

            binding.textViewMovieOverview.text = upcomingMovie.overview

            // Set click listener
            binding.imageViewMoviePoster.setOnClickListener {
                val args = Bundle()
                args.putInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY, upcomingMovie.id)
                Navigation.findNavController(it).navigate(R.id.movieDetailsFragment, args)
            }
        }

        companion object {
            fun create(parent: ViewGroup): MovieItemViewHolder {
                val binding = ItemUpcomingMovieBinding
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