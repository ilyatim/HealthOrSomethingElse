package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.AbsViewHolder
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemWeekStatisticsBinding
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.home.WeekValuesAdapter

class WeekStatViewHolder(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemWeekStatisticsBinding =
        ItemWeekStatisticsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Statistics>(binding.root) {
    override fun bind(cell: Statistics) {
        cell as Statistics.WeekStatistics
        binding.recyclerView.adapter = WeekValuesAdapter(layoutInflater, cell.items.toMutableList())
    }
}