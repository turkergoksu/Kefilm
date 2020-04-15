package me.turkergoksu.kefilm.ui.toprated

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.databinding.ItemTopratedMovieBinding
import me.turkergoksu.kefilm.model.genre.Genre
import me.turkergoksu.kefilm.model.toprated.TopRatedMovieItem
import me.turkergoksu.kefilm.utils.StringUtil
import java.text.SimpleDateFormat

/**
 * Created by turkergoksu on 13-Apr-20, 12:11 AM
 */

class TopRatedMovieAdapter :
    RecyclerView.Adapter<TopRatedMovieAdapter.MovieItemViewHolder>() {

    private val topRatedMovieList = arrayListOf<TopRatedMovieItem>()
    private var genreHashMap = mapOf<Int, Genre>()

    fun setTopRatedMovieList(topRatedMovieList: List<TopRatedMovieItem>) {
        this.topRatedMovieList.clear()
        this.topRatedMovieList.addAll(topRatedMovieList)
        notifyDataSetChanged()
    }

    fun setGenreMap(genreHashMap: Map<Int, Genre>) {
        this.genreHashMap = genreHashMap
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder.create(parent)

    override fun getItemCount(): Int = topRatedMovieList.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) =
        holder.bind(topRatedMovieList[position], position, genreHashMap)

    class MovieItemViewHolder(private val binding: ItemTopratedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(
            topRatedMovie: TopRatedMovieItem,
            position: Int,
            genreHashMap: Map<Int, Genre>
        ) {
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    topRatedMovie.posterPath
                )
            ).into(binding.imageViewMoviePoster)

            binding.textViewMovieItemIndex.text = "%s.".format(position + 1)

            binding.textViewMovieTitle.text = topRatedMovie.title

            binding.textViewMovieReleaseYear.text = "(%s)".format(
                StringUtil.getYearFromDate(
                    topRatedMovie.releaseDate,
                    SimpleDateFormat(Constants.MOVIE_DB_DATE_FORMAT)
                )
            )

            val genreText = StringBuilder()
            for (genreId in topRatedMovie.genreIds) {
                if (genreHashMap[genreId] != null) {
                    genreText.append((genreHashMap[genreId])!!.name)
                        .append(" ")
                }

            }
            binding.textViewMovieGenres.text = genreText.toString()

            binding.textViewMovieOverview.text = topRatedMovie.overview
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