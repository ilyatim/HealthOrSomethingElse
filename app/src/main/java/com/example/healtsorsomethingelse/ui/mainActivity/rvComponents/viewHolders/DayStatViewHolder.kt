package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemDayStatisticsBinding
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.adapters.DayValuesAdapter

class DayStatViewHolder(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayStatisticsBinding =
        ItemDayStatisticsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder(binding.root) {
    override fun bind(item: Statistics) {
        item as Statistics.DayStatistics
        binding.recyclerView.adapter = DayValuesAdapter(layoutInflater, item.items.toMutableList())
    }
}