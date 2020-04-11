package me.turkergoksu.kefilm.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_main.view.*
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding

/**
 * Created by turkergoksu on 12-Apr-20, 12:44 AM
 */

object ImageLoadingUtil {

    fun changeMainFragmentBackground(
        context: Context,
        binding: FragmentUpcomingBinding,
        posterPath: String
    ) {
        Glide.with(context).asBitmap()
            .apply(
                RequestOptions.bitmapTransform(
                    BlurTransformation(
                        Constants.UPCOMING_MOVIE_ITEM_BLUR_RADIUS,
                        Constants.UPCOMING_MOVIE_ITEM_BLUR_SAMPLING_VALUE
                    )
                )
            ).load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    posterPath
                )
            ).into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    val drawable = BitmapDrawable(context.resources, resource)
                    // ... I assume ...
                    // binding.root = fragment_upcoming
                    // binding.root.rootView = viewpager
                    // binding.root.rootView.rootView = fragment_main
                    binding.root.rootView.rootView.layout_main.background = drawable
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })
    }
}