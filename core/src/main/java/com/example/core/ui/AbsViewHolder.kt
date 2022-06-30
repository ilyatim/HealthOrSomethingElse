package com.example.core.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbsViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(cell: T)

    fun getContext(): Context = itemView.context
}
