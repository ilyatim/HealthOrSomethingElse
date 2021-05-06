package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.databinding.ItemAddPurposeBinding
import com.example.healtsorsomethingelse.databinding.ItemPurposesBinding

class PlusAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: AddPurposeListener
) : RecyclerView.Adapter<PlusAdapter.ViewHolder>() {

    private val itemCount = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater, parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = itemCount

    inner class ViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        private val listener: AddPurposeListener,
        private val binding: ItemAddPurposeBinding =
            ItemAddPurposeBinding.inflate(
                layoutInflater,
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.plusButton.setOnClickListener { listener.addPurpose() }
        }
    }
}

fun interface AddPurposeListener {
    fun addPurpose()
}