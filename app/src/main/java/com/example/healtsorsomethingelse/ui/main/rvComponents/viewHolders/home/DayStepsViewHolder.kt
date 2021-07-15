package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayStepsBinding
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class DayStepsViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayStepsBinding =
        ItemDayStepsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<DayStatisticsItem>(binding.root) {
    override fun bind(cell: DayStatisticsItem) {
        //TODO: inflate
    }
}