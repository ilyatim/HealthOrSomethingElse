package com.example.healtsorsomethingelse.utils

import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.database.HorizontalSublistAdapter

object UiUtils {
    fun setHolderBottomMargin(
        holder: RecyclerView.ViewHolder,
        bottomMargin: Int
    ) {
        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
        params.bottomMargin = bottomMargin
        holder.itemView.layoutParams = params
    }

    fun setHolderTopMargin(
        holder: RecyclerView.ViewHolder,
        topMargin: Int,
    ) {
        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
        params.topMargin = topMargin
        holder.itemView.layoutParams = params
    }

    fun setHolderEndMargin(
        holder: RecyclerView.ViewHolder,
        marginEnd: Int
    ) {
        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
        params.marginEnd = marginEnd
        holder.itemView.layoutParams = params
    }

    fun setHolderStartMargin(
        holder: RecyclerView.ViewHolder,
        marginStart: Int
    ) {
        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
        params.marginStart = marginStart
        holder.itemView.layoutParams = params
    }
}