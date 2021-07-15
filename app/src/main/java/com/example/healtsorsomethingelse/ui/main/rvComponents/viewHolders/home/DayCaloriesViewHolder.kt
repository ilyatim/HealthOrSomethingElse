package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayCaloriesBinding
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class DayCaloriesViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayCaloriesBinding =
        ItemDayCaloriesBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<DayStatisticsItem>(binding.root) {
    override fun bind(cell: DayStatisticsItem) {
        //TODO: inflate
    }
}