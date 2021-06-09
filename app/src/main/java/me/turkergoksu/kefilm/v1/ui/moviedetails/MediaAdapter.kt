package me.turkergoksu.kefilm.v1.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import me.turkergoksu.kefilm.databinding.ItemMediaBinding
import me.turkergoksu.kefilm.v1.Constants
import me.turkergoksu.kefilm.v1.model.moviedetails.Backdrop

/**
 * Created by turkergoksu on 23-Jun-20, 10:48 PM
 */

class MediaAdapter(private val backdropItemList: List<Backdrop>, private val clickCallback: (Int) -> Unit) :
    RecyclerView.Adapter<MediaAdapter.MediaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder =
        MediaItemViewHolder.create(parent)

    override fun getItemCount(): Int = backdropItemList.size

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) =
        holder.bind(backdropItemList[position], clickCallback, position)

    class MediaItemViewHolder(private val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(backdropItem: Backdrop, clickCallback: (Int) -> Unit, position: Int) {
            Glide.with(binding.root.context).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    backdropItem.filePath
                )
            ).apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        Constants.MOVIE_DETAILS_MEDIA_ITEM_CORNER_RADIUS,
                        0
                    )
                )
            ).into(binding.imageViewMedia)

            binding.imageViewMedia.setOnClickListener {
                clickCallback.invoke(position)
            }
        }

        companion object {
            fun create(parent: ViewGroup): MediaItemViewHolder {
                val binding = ItemMediaBinding
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