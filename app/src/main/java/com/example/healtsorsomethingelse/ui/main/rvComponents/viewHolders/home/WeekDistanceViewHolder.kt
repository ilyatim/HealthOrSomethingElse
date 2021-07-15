package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemWeekDistanceBinding
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class WeekDistanceViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemWeekDistanceBinding =
        ItemWeekDistanceBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<WeekStatisticsItem>(binding.root) {
    override fun bind(cell: WeekStatisticsItem) {
        //TODO: inflate
    }
}