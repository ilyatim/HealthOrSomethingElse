package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.ItemDatabaseSublistBinding
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.HorizontalSublistAdapter
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.utils.AbsViewHolder

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
) : AbsViewHolder<UserDatabaseContent>(binding.root){
    private lateinit var adapter: HorizontalSublistAdapter

    override fun bind(cell: UserDatabaseContent) {
        //TODO: save list position
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
    }
}