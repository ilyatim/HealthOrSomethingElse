package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.databinding.ItemPurposesBinding

class Adapter(
    private val layoutInflater: LayoutInflater,
    private val items: MutableList<String>,
    private val listener: PurposesAdapterListener
) : RecyclerView.Adapter<Adapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            layoutInflater,
            parent,
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        private val listener: PurposesAdapterListener,
        private val binding: ItemPurposesBinding =
            ItemPurposesBinding.inflate(
                layoutInflater,
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {

        }
    }
}

interface PurposesAdapterListener {
    fun onCompleteClick(value: String, adapterPosition: Int)
    fun onDismissClick(adapterPosition: Int)
}
