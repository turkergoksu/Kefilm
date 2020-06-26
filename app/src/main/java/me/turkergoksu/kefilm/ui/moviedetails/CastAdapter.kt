package me.turkergoksu.kefilm.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.databinding.ItemMovieDetailsCastBinding
import me.turkergoksu.kefilm.model.moviedetails.CastItem

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