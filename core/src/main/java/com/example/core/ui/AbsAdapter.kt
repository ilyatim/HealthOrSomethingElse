package com.example.core.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstract adapter for recycler view
 * @param T view holder data cell type
 * @param V view holder
 * @param list content list
 */
abstract class AbsAdapter<T, V: AbsViewHolder<T>>(
    protected val list: MutableList<T>
) : RecyclerView.Adapter<V>() {

    /**
     * Update current list with [newList]
     * Use [DiffUtil.Callback]
     * @param newList list with new cell's, might be empty
     */
    open fun updateList(newList: List<T>) {
        val diffUtil = getDiffUtils(list, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)
        this.list.clear()
        this.list.addAll(newList)
    }

    protected abstract fun getDiffUtils(oldList: List<T>, newList: List<T>): DiffUtil.Callback

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(list[position])
    }
}

/**
 * Abstract adapter for different recycler view holder's
 * @param T view holder data cell type
 * @param V view holder
 * @param E enum for different view holder
 * @param list content list
 */
abstract class AbsMultipleAdapter<T, V: AbsViewHolder<T>, E: Enum<*>>(
    list: MutableList<T>
): AbsAdapter<T, V>(list) {
    /**
     * Property for determining the type of view
     */
    protected abstract val T.viewType: E

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType.ordinal
    }
}