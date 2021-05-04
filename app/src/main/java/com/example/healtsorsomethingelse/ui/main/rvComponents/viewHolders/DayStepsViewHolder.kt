package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayStepsBinding

class DayStepsViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayStepsBinding =
        ItemDayStepsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsDayViewHolder(binding.root) {
    override fun bind(item: DayStatisticsItem) {
        //TODO: inflate
    }
}