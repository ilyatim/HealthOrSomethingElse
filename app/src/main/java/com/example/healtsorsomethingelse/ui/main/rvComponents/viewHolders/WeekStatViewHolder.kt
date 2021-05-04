package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemWeekStatisticsBinding
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.WeekValuesAdapter

class WeekStatViewHolder(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemWeekStatisticsBinding =
        ItemWeekStatisticsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder(binding.root) {
    override fun bind(item: Statistics) {
        item as Statistics.WeekStatistics
        binding.recyclerView.adapter = WeekValuesAdapter(layoutInflater, item.items.toMutableList())
    }
}