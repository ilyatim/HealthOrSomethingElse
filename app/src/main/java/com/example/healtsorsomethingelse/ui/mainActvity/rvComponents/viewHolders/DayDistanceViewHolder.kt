package com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayDistanceBinding

class DayDistanceViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayDistanceBinding =
        ItemDayDistanceBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsDayViewHolder(binding.root) {
    override fun bind(item: DayStatisticsItem) {
        TODO("Not yet implemented")
    }
}