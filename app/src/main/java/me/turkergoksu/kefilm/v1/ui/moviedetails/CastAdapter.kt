package me.turkergoksu.kefilm.v1.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemMovieDetailsCastBinding
import me.turkergoksu.kefilm.v1.Constants
import me.turkergoksu.kefilm.v1.model.moviedetails.CastItem

/**
 * Created by turkergoksu on 23-Jun-20, 4:24 PM
 */

class CastAdapter(private val castItemList: List<CastItem>) :
    RecyclerView.Adapter<CastAdapter.CastItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder =
        CastItemViewHolder.create(parent)

    override fun getItemCount(): Int = castItemList.size

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) =
        holder.bind(castItemList[position])

    class CastItemViewHolder(private val binding: ItemMovieDetailsCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(castItem: CastItem) {
            if (castItem.profilePath != null) {
                Glide.with(binding.root.context).load(
                    "%s%s".format(
                        Constants.API_IMAGE_URL,
                        castItem.profilePath
                    )
                ).circleCrop().into(binding.imageViewCastPhoto)
            }

            binding.textViewCastName.text = castItem.name

            binding.imageViewCastPhoto.setOnClickListener {
                val args = Bundle()
                args.putInt(Constants.PEOPLE_DETAILS_PEOPLE_ID_ARG_KEY, castItem.id)

                Navigation.findNavController(it).navigate(R.id.peopleDetailsFragment, args)
            }

            binding.root.setOnClickListener {
                binding.imageViewCastPhoto.performClick()
            }
        }

        companion object {
            fun create(parent: ViewGroup): CastItemViewHolder {
                val binding = ItemMovieDetailsCastBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CastItemViewHolder(binding)
            }
        }
    }
}