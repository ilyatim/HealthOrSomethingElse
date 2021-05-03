package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemWeekDistanceBinding

class WeekDistanceViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemWeekDistanceBinding =
        ItemWeekDistanceBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsWeekViewHolder(binding.root) {
    override fun bind(item: WeekStatisticsItem) {
        TODO("Not yet implemented")
    }
}