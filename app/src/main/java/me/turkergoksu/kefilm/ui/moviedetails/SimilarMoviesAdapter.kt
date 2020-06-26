package me.turkergoksu.kefilm.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemMovieDetailsSimilarMovieBinding
import me.turkergoksu.kefilm.model.moviedetails.SimilarMovie

/**
 * Created by turkergoksu on 24-Jun-20, 6:19 PM
 */

class SimilarMoviesAdapter(private val similarMovieList: List<SimilarMovie>) :
    RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMovieItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieItemViewHolder =
        SimilarMovieItemViewHolder.create(parent)

    override fun getItemCount(): Int = similarMovieList.size

    override fun onBindViewHolder(holder: SimilarMovieItemViewHolder, position: Int) =
        holder.bind(similarMovieList[position])

    class SimilarMovieItemViewHolder(private val binding: ItemMovieDetailsSimilarMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(similarMovieItem: SimilarMovie) {
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    similarMovieItem.posterPath
                )
            ).apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        Constants.MOVIE_DETAILS_MEDIA_ITEM_CORNER_RADIUS,
                        0
                    )
                )
            ).into(binding.imageViewSimilarMoviePoster)

            binding.root.setOnClickListener {
                val args = Bundle()
                args.putInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY, similarMovieItem.id)

                Navigation.findNavController(it).navigate(R.id.movieDetailsFragment, args)
            }
        }

        companion object {
            fun create(parent: ViewGroup): SimilarMovieItemViewHolder {
                val binding =
                    ItemMovieDetailsSimilarMovieBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return SimilarMovieItemViewHolder(binding)
            }
        }
    }
}