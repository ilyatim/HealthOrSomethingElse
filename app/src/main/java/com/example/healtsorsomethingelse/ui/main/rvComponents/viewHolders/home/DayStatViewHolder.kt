package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemDayStatisticsBinding
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.home.DayValuesAdapter
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class DayStatViewHolder(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemDayStatisticsBinding =
        ItemDayStatisticsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Statistics>(binding.root) {
    override fun bind(cell: Statistics) {
        cell as Statistics.DayStatistics
        binding.recyclerView.adapter = DayValuesAdapter(layoutInflater, cell.items.toMutableList())
    }
}