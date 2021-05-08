package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem

abstract class AbsDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: DayStatisticsItem)
}