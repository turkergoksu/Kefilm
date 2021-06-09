package me.turkergoksu.kefilm.upcoming.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import me.turkergoksu.kefilm.core.BaseAdapter
import me.turkergoksu.kefilm.databinding.ItemUpcomingMovieBinding
import me.turkergoksu.kefilm.upcoming.data.UpcomingMovieItem

/**
 * Created by turkergoksu on 09-Jun-21.
 */
class UpcomingMovieItemAdapter :
    BaseAdapter<UpcomingMovieItem, UpcomingMovieItemAdapter.UpcomingMovieItemViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UpcomingMovieItemViewHolder(
        ItemUpcomingMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UpcomingMovieItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class UpcomingMovieItemViewHolder(val binding: ItemUpcomingMovieBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(item: UpcomingMovieItem) {
            binding.item = item

            binding.imageViewPoster.setOnClickListener {
                itemClickListener?.invoke(item)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UpcomingMovieItem>() {
            override fun areItemsTheSame(oldItem: UpcomingMovieItem, newItem: UpcomingMovieItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UpcomingMovieItem,
                newItem: UpcomingMovieItem
            ) =
                oldItem == newItem
        }
    }
}