package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemSleepBinding

class SleepViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemSleepBinding =
        ItemSleepBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder(binding.root) {
    override fun bind(item: Statistics) {
        //TODO: inflate
    }
}