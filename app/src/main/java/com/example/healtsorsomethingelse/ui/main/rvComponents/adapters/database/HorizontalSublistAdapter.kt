package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.database.mainScreen.ContentItem
import com.example.healtsorsomethingelse.databinding.ItemDatabaseSublistItemBinding
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.utils.DiffUtilCallbackImpl
import com.example.healtsorsomethingelse.utils.UiUtils

class HorizontalSublistAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: DatabaseListener,
    diffUtil: DiffUtilCallbackImpl<ContentItem> = DiffUtilCallbackImpl()
) : ListAdapter<ContentItem, HorizontalSublistAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater, parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0 && currentList.size > 0) {
            UiUtils.setHolderStartMargin(holder, holder.itemView.marginStart * 2)
        }
        if (position == this.currentList.lastIndex) {
            UiUtils.setHolderEndMargin(holder, holder.itemView.marginStart * 2)
        }
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        private val listener: DatabaseListener,
        private val binding: ItemDatabaseSublistItemBinding =
            ItemDatabaseSublistItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cell: ContentItem) {
            binding.root.setOnClickListener { listener.onSubRecyclerCellClick(cell.itemId) }
            binding.topicTextView.text = cell.subTitle
            /*Glide.with(binding.imageView)
                .load(cell.foregroundImage)
                .into(binding.imageView)*/
        }
    }
}



