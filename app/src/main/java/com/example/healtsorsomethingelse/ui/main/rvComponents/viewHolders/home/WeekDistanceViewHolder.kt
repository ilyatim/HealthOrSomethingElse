package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.AbsBindingViewHolder
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemWeekDistanceBinding

class WeekDistanceViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
) : AbsBindingViewHolder<WeekStatisticsItem, ItemWeekDistanceBinding>(
    ItemWeekDistanceBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {
    override fun bind(cell: WeekStatisticsItem) {
        //TODO: inflate
    }
}