package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.ItemDatabaseSublistBinding
import com.example.healtsorsomethingelse.di.ConstManagerInterface
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.HorizontalSublistAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.utils.AbsViewHolder
import com.example.healtsorsomethingelse.utils.database.StateHandler
import dagger.hilt.EntryPoints

class SubListViewHolder(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: DatabaseListener,
    private val binding: ItemDatabaseSublistBinding =
        ItemDatabaseSublistBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<UserDatabaseContent>(binding.root) {

    private lateinit var adapter: HorizontalSublistAdapter

    private var constManager: StateHandler =
        EntryPoints.get(binding.root.context, ConstManagerInterface::class.java).getStateHandler()

    override fun bind(cell: UserDatabaseContent) {
        cell as UserDatabaseContent.ContentList

        adapter = HorizontalSublistAdapter(layoutInflater, listener)
        adapter.submitList(cell.content)

        binding.topicTextView.text = cell.topic
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            layoutInflater.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        constManager.getViewHolderPosition(bindingAdapterPosition)?.let {
            binding.recyclerView.scrollToPosition(it)
        }
    }

    //add fun onDetachViewHolder that save position in StateHandler
}

