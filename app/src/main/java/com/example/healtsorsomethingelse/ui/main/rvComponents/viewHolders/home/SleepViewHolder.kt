package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemSleepBinding
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class SleepViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemSleepBinding =
        ItemSleepBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Statistics>(binding.root) {
    override fun bind(cell: Statistics) {
        //TODO: inflate
    }
}