package me.turkergoksu.kefilm.ui.media

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.ItemFullscreenMediaBinding
import me.turkergoksu.kefilm.model.moviedetails.Backdrop

/**
 * Created by turkergoksu on 11-Feb-21.
 */
class ImageAdapter(private val backdropList: List<Backdrop>) :
    RecyclerView.Adapter<ImageAdapter.MediaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder =
        MediaItemViewHolder.create(parent)

    override fun getItemCount(): Int = backdropList.size

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) =
        holder.bind(backdropList[position])

    class MediaItemViewHolder(private val binding: ItemFullscreenMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(backdropItem: Backdrop) {
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    backdropItem.filePath
                )
            ).into(
                object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        binding.photoViewImage.setImageDrawable(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }

        companion object {
            fun create(parent: ViewGroup): MediaItemViewHolder {
                val binding = ItemFullscreenMediaBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return MediaItemViewHolder(binding)
            }

        }
    }
}