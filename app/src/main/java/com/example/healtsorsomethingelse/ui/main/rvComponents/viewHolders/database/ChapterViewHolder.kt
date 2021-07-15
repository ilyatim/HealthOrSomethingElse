package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.database.mainScreen.Cell
import com.example.healtsorsomethingelse.databinding.ItemChapterDatabaseBinding
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class ChapterViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: DatabaseListener,
    private val binding: ItemChapterDatabaseBinding =
        ItemChapterDatabaseBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Cell>(binding.root) {

    override fun bind(cell: Cell) {
        //TODO: Inflate
    }
}