package com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.core.ui.AbsBindingViewHolder
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.databinding.ItemChapterDatabaseBinding
import com.example.healtsorsomethingelse.ui.main.vpComponents.DatabaseListener

class ChapterViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: DatabaseListener,
) : AbsBindingViewHolder<UserDatabaseContent, ItemChapterDatabaseBinding>(
    ItemChapterDatabaseBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {

    override fun bind(cell: UserDatabaseContent) = withBinding {
        //binding.cardView.backgroundTintList
        cell as UserDatabaseContent.Block
        //TODO: handle color tint
        textView.text = cell.topic
        /*Glide.with(binding.imageView)
            .load(cell.imageUrl)
            .into(binding.imageView)*/
    }
}