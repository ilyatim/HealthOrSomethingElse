package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.databinding.ItemAdviceBinding
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class AdviceViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemAdviceBinding =
        ItemAdviceBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Statistics>(binding.root) {
    override fun bind(cell: Statistics) {
        cell as Statistics.Advice
        binding.adviceTextView.text = cell.advice
    }
}