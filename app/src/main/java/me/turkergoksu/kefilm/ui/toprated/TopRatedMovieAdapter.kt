package me.turkergoksu.kefilm.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemTopratedMovieBinding
import me.turkergoksu.kefilm.model.genre.Genre
import me.turkergoksu.kefilm.model.toprated.TopRatedMovieItem
import me.turkergoksu.kefilm.utils.StringUtil

/**
 * Created by turkergoksu on 14-Jun-20, 3:35 AM
 */

class TopRatedMovieAdapter :
    PagedListAdapter<TopRatedMovieItem, TopRatedMovieAdapter.MovieItemViewHolder>(
        TopRatedMovieItem.CALLBACK
    ) {

    private var genreHashMap = mapOf<Int, Genre>()

    fun setGenreMap(genreHashMap: Map<Int, Genre>) {
        this.genreHashMap = genreHashMap
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieItemViewHolder {
        return MovieItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position), position, genreHashMap)
    }

    class MovieItemViewHolder(private val binding: ItemTopratedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            topRatedMovie: TopRatedMovieItem?,
            position: Int,
            genreHashMap: Map<Int, Genre>
        ) {
            // Set movie's poster image
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    topRatedMovie!!.posterPath
                )
            ).into(binding.imageViewMoviePoster)

            // Set movie index
            binding.textViewMovieItemIndex.text = "%s.".format(position + 1)

            // Set movie title
            binding.textViewMovieTitle.text = topRatedMovie.title

            // Set movie release date
            binding.textViewMovieReleaseYear.text = "(%s)".format(
                StringUtil.getYearFromDate(
                    topRatedMovie.releaseDate,
                    Constants.MOVIE_DB_DATE_FORMAT
                )
            )

            // Set movie average score
            binding.percentageViewMovieAvg.setPercentage((topRatedMovie.voteAverage * 10).toInt())

            // Set movie's genres
            val genreText = StringBuilder()
            for (genreId in topRatedMovie.genreIds) {
                if (genreHashMap[genreId] != null) {
                    genreText.append((genreHashMap[genreId])!!.name)
                        .append(" ")
                }

            }
            binding.textViewMovieGenres.text = genreText.toString()

            // Set movie runtime text
            // TODO: 11-Feb-21
//            binding.textViewMovieRuntime.text = "(%s)".format()

            // Set movie's overview
            binding.textViewMovieOverview.text = topRatedMovie.overview

            // Set click listener
            binding.root.setOnClickListener {
                val args = Bundle()
                args.putInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY, topRatedMovie.id)

                Navigation.findNavController(it).navigate(R.id.movieDetailsFragment, args)
            }
        }

        companion object {
            fun create(parent: ViewGroup): MovieItemViewHolder {
                val binding = ItemTopratedMovieBinding
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