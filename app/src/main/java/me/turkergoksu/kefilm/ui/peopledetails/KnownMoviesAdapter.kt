package me.turkergoksu.kefilm.ui.peopledetails

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
import me.turkergoksu.kefilm.databinding.ItemKnownMoviesBinding
import me.turkergoksu.kefilm.model.people.KnownMovie

/**
 * Created by turkergoksu on 11-Feb-21.
 */
class KnownMoviesAdapter(private val knownMovieList: List<KnownMovie>) :
    RecyclerView.Adapter<KnownMoviesAdapter.KnownMovieItemViewHolder>() {

    class KnownMovieItemViewHolder(private val binding: ItemKnownMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(knownMovieItem: KnownMovie) {
                // Set poster
                if (knownMovieItem.posterPath != null) {
                    Glide.with(binding.root.context).load(
                        "%s%s".format(
                            Constants.API_IMAGE_URL,
                            knownMovieItem.posterPath
                        )
                    ).apply(
                        RequestOptions.bitmapTransform(
                            RoundedCornersTransformation(
                                Constants.MOVIE_DETAILS_MEDIA_ITEM_CORNER_RADIUS,
                                0
                            )
                        )
                    ).into(binding.imageViewKnownMoviePoster)
                }

                // Set movie name
                binding.textViewMovieName.text = knownMovieItem.title

                // Set character name
                binding.textViewCharacterName.text = knownMovieItem.characterName

                // Click listener
                binding.imageViewKnownMoviePoster.setOnClickListener {
                    val args = Bundle()
                    knownMovieItem.id?.let { id ->
                        args.putInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY,
                            id
                        )
                    }

                    Navigation.findNavController(it).navigate(R.id.movieDetailsFragment, args)
                }
            }

        companion object {
            fun create(parent: ViewGroup): KnownMovieItemViewHolder {
                val binding =
                    ItemKnownMoviesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return KnownMovieItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnownMovieItemViewHolder =
        KnownMovieItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: KnownMovieItemViewHolder, position: Int) =
        holder.bind(knownMovieList[position])

    override fun getItemCount(): Int = knownMovieList.size
}