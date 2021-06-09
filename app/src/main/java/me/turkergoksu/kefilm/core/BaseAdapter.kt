package me.turkergoksu.kefilm.core

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by turkergoksu on 09-Jun-21.
 */
abstract class BaseAdapter<ITEM, VH : RecyclerView.ViewHolder>
constructor(diffCallback: DiffUtil.ItemCallback<ITEM>) : ListAdapter<ITEM, VH>(diffCallback) {

    var itemClickListener: ((ITEM) -> Unit)? = null

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: ITEM)
    }
}