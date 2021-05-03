package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.home.Statistics

abstract class AbsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: Statistics)
}