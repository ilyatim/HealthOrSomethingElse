package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayWorkoutBinding

class DayWorkoutViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayWorkoutBinding =
        ItemDayWorkoutBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsDayViewHolder(binding.root) {
    override fun bind(item: DayStatisticsItem) {
        //TODO: inflate
    }
}
