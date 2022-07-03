package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.core.ui.AbsAdapter
import com.example.core.ui.AbsBindingViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.databinding.ItemPurposesBinding

class Adapter(
    private val layoutInflater: LayoutInflater,
    private val listener: PurposesListener,
    list: MutableList<String>,
) : AbsAdapter<String, ViewHolder>(list){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            layoutInflater,
            parent,
            listener
        )
    }

    override fun getDiffUtils(
        oldList: List<String>,
        newList: List<String>
    ): DiffUtil.Callback = DiffUtilImpl(oldList, newList)
}

class ViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: PurposesListener,
) : AbsBindingViewHolder<String, ItemPurposesBinding>(
    ItemPurposesBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {
    override fun bind(cell: String) = withBinding {
        purposeTextView.text = cell
        buttonDone.setOnClickListener { listener.onCompleteClick(cell, bindingAdapterPosition) }
        buttonRemove.setOnClickListener { listener.onDismissClick(bindingAdapterPosition) }
    }
}

interface PurposesListener {
    fun onCompleteClick(value: String, adapterPosition: Int)
    fun onDismissClick(adapterPosition: Int)
}
