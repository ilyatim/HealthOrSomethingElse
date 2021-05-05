package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayCaloriesBinding

class DayCaloriesViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayCaloriesBinding =
        ItemDayCaloriesBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsDayViewHolder(binding.root) {
    override fun bind(item: DayStatisticsItem) {
        //TODO: inflate
    }
}