package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
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
) : AbsViewHolder<UserDatabaseContent>(binding.root) {

    override fun bind(cell: UserDatabaseContent) {
        //binding.cardView.backgroundTintList
        cell as UserDatabaseContent.Block
        //TODO: handle color tint
        binding.textView.text = cell.topic
        /*Glide.with(binding.imageView)
            .load(cell.imageUrl)
            .into(binding.imageView)*/
    }
}