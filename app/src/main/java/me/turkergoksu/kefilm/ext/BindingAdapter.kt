package me.turkergoksu.kefilm.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.core.BaseAdapter

/**
 * Created by turkergoksu on 11-Jun-21.
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("android:items")
fun <ITEM> RecyclerView.items(items: List<ITEM>?) {
    if (adapter != null && adapter is BaseAdapter<*, *>) {
        items.let { (adapter as BaseAdapter<ITEM, *>).submitList(it) }
    }
}

@BindingAdapter("android:image")
fun ImageView.image(url: String?) {
    Glide.with(context)
        .load(Constants.API_IMAGE_URL + url)
        .into(this)
}