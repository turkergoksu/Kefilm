package me.turkergoksu.kefilm.ui.upcoming

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.databinding.ItemUpcomingMovieBinding
import me.turkergoksu.kefilm.model.upcoming.UpcomingMovieItem
import me.turkergoksu.kefilm.utils.StringUtil
import java.text.SimpleDateFormat

/**
 * Created by turkergoksu on 30-Mar-20, 8:19 PM
 */

class UpcomingMovieAdapter :
    RecyclerView.Adapter<UpcomingMovieAdapter.MovieItemViewHolder>() {

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
            )
                .into(binding.imageViewMoviePoster)

            binding.textViewMovieTitle.text = upcomingMovie.title

            binding.textViewMovieReleaseDate.text = StringUtil.formatDate(
                upcomingMovie.releaseDate,
                SimpleDateFormat(Constants.MOVIE_DB_DATE_FORMAT),
                SimpleDateFormat(Constants.UPCOMING_MOVIE_DATE_FORMAT)
            )

            binding.textViewMovieOverview.text = upcomingMovie.overview
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