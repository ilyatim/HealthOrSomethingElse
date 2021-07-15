package com.example.healtsorsomethingelse.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbsViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(cell: T)
}