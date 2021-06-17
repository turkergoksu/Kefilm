package me.turkergoksu.kefilm.now_playing.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import me.turkergoksu.kefilm.core.BaseAdapter
import me.turkergoksu.kefilm.databinding.ItemNowPlayingMovieBinding
import me.turkergoksu.kefilm.now_playing.data.NowPlayingMovieItem

/**
 * Created by turkergoksu on 09-Jun-21.
 */
class NowPlayingMovieItemAdapter :
    BaseAdapter<NowPlayingMovieItem, NowPlayingMovieItemAdapter.NowPlayingMovieItemViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NowPlayingMovieItemViewHolder(
            ItemNowPlayingMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NowPlayingMovieItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class NowPlayingMovieItemViewHolder(val binding: ItemNowPlayingMovieBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(item: NowPlayingMovieItem) {
            binding.item = item

            binding.cardViewPoster.setOnClickListener {
                itemClickListener?.invoke(item)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NowPlayingMovieItem>() {
            override fun areItemsTheSame(
                oldItem: NowPlayingMovieItem,
                newItem: NowPlayingMovieItem
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: NowPlayingMovieItem,
                newItem: NowPlayingMovieItem
            ) =
                oldItem == newItem
        }
    }
}