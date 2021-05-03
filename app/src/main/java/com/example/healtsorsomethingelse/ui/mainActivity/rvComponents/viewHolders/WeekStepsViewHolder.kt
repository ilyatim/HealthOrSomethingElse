package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemWeekStepsBinding

class WeekStepsViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemWeekStepsBinding =
        ItemWeekStepsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsWeekViewHolder(binding.root) {
    override fun bind(item: WeekStatisticsItem) {
        TODO("Not yet implemented")
    }
}