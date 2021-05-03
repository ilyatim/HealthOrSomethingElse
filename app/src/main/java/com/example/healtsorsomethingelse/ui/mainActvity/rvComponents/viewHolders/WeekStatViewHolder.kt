package com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemWeekStatisticsBinding
import com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders.AbsViewHolder

class WeekStatViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemWeekStatisticsBinding =
        ItemWeekStatisticsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder(binding.root) {
    override fun bind(item: Statistics) {
        TODO("Not yet implemented")
    }
}