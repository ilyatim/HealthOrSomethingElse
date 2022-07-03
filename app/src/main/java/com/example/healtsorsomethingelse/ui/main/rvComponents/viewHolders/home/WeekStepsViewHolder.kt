package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.AbsBindingViewHolder
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.databinding.ItemWeekStepsBinding

class WeekStepsViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
) : AbsBindingViewHolder<WeekStatisticsItem, ItemWeekStepsBinding>(
    ItemWeekStepsBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {
    override fun bind(cell: WeekStatisticsItem) {
        //TODO: Inflate
    }
}