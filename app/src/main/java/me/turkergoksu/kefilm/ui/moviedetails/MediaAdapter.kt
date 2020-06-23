package me.turkergoksu.kefilm.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.databinding.ItemMediaBinding
import me.turkergoksu.kefilm.model.moviedetails.Backdrop

/**
 * Created by turkergoksu on 23-Jun-20, 10:48 PM
 */

class MediaAdapter :
        RecyclerView.Adapter<MediaAdapter.MediaItemViewHolder>() {

    private val backdropItemList = arrayListOf<Backdrop>()

    fun setBackdropItemList(backdropItemList: List<Backdrop>){
        this.backdropItemList.clear()
        this.backdropItemList.addAll(backdropItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder =
        MediaItemViewHolder.create(parent)

    override fun getItemCount(): Int = backdropItemList.size

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) =
        holder.bind(backdropItemList[position])

    class MediaItemViewHolder(private val binding: ItemMediaBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(backdropItem: Backdrop){
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