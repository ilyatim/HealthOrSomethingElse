package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.AbsBindingViewHolder
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemDayCaloriesBinding

class DayCaloriesViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
) : AbsBindingViewHolder<DayStatisticsItem, ItemDayCaloriesBinding>(
    ItemDayCaloriesBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {
    override fun bind(cell: DayStatisticsItem) {
        //TODO: inflate
    }
}