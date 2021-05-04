package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemAdviceBinding

class AdviceViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemAdviceBinding =
        ItemAdviceBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder(binding.root) {
    override fun bind(item: Statistics) {
        item as Statistics.Advice
        binding.adviceTextView.text = item.advice
    }
}