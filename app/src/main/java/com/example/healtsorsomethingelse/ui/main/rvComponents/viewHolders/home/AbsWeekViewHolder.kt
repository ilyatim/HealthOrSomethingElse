package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem

abstract class AbsWeekViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: WeekStatisticsItem)
}