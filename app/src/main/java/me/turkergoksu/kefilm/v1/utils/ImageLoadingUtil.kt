package me.turkergoksu.kefilm.v1.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.transition.TransitionFactory
import jp.wasabeef.glide.transformations.BlurTransformation
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.v1.Constants

/**
 * Created by turkergoksu on 12-Apr-20, 12:44 AM
 */

object ImageLoadingUtil {

    fun resetMainFragmentBackground(
        context: Context,
        mainFragmentImageView: ImageView
    ) {
        Glide.with(context)
            .load(R.color.defaultBackground).into(mainFragmentImageView)
    }

    fun changeImageWithCrossFadeTransition(
        context: Context,
        imageView: ImageView,
        posterPath: String,
        duration: Int
    ) {
        Glide
            .with(context)
            .asDrawable()
            .transition(
                DrawableTransitionOptions.with(object : TransitionFactory<Drawable> {
                    override fun build(
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Transition<Drawable>? {
                        return DrawableCrossFadeTransition(duration, true)
                    }
                })
            )
            .apply(
                RequestOptions.bitmapTransform(
                    BlurTransformation(
                        Constants.UPCOMING_MOVIE_ITEM_BLUR_RADIUS,
                        Constants.UPCOMING_MOVIE_ITEM_BLUR_SAMPLING_VALUE
                    )
                )
            )
            .load(
                "%s%s".format(
                    Constants.API_IMAGE_URL,
                    posterPath
                )
            )
            .into(
                object : CustomTarget<Drawable>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        TODO("Not yet implemented")
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        val isTransitionSucceed = transition?.transition(
                            resource,
                            DrawableImageViewTarget(imageView)
                        )

                        if (isTransitionSucceed!!.not()) {
                            imageView.setImageDrawable(resource)
                        }
                    }

                })
    }
}