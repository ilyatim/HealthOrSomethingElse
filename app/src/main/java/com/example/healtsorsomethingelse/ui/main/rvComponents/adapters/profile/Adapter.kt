package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.databinding.ItemPurposesBinding
import com.example.healtsorsomethingelse.utils.DiffUtilImpl

class Adapter(
    private val layoutInflater: LayoutInflater,
    private val items: MutableList<String>,
    private val listener: PurposesListener
) : RecyclerView.Adapter<Adapter.ViewHolder>(){

    private lateinit var diffUtil: DiffUtilImpl<String>

    fun updateList(newList: List<String>) {
        diffUtil = DiffUtilImpl(items, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)

        diffUtilResult.dispatchUpdatesTo(this)

        items.clear()
        items.addAll(newList)
    }

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
        private val listener: PurposesListener,
        private val binding: ItemPurposesBinding =
            ItemPurposesBinding.inflate(
                layoutInflater,
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.purposeTextView.text = item
            binding.buttonDone.setOnClickListener { listener.onCompleteClick(item, bindingAdapterPosition) }
            binding.buttonRemove.setOnClickListener { listener.onDismissClick(bindingAdapterPosition) }
        }
    }
}

interface PurposesListener {
    fun onCompleteClick(value: String, adapterPosition: Int)
    fun onDismissClick(adapterPosition: Int)
}
