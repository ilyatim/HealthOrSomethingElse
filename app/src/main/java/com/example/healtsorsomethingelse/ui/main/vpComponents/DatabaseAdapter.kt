package com.example.healtsorsomethingelse.ui.main.vpComponents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.database.mainScreen.Cell
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database.ChapterViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database.SubListViewHolder
import com.example.healtsorsomethingelse.utils.AbsViewHolder
import com.example.healtsorsomethingelse.utils.DiffUtilImpl

class DatabaseAdapter(
    private val layoutInflater: LayoutInflater,
    private val content: MutableList<Cell>,
    private val listener: DatabaseListener,
) : RecyclerView.Adapter<AbsViewHolder<Cell>>() {

    private lateinit var diffUtilImpl: DiffUtilImpl<Cell>
    private val viewTypeValues = ViewType.values()
    private val Cell.viewType: ViewType
        get() = when (this) {
            is Cell.ChapterCell -> ViewType.CHAPTER
            is Cell.SubRecyclerCell -> ViewType.SUBRECYCLER
        }

    fun updateList(newList: List<Cell>) {
        diffUtilImpl = DiffUtilImpl(content, newList)
        val diffUtilsResult = DiffUtil.calculateDiff(diffUtilImpl)
        diffUtilsResult.dispatchUpdatesTo(this)

        content.clear()
        content.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<Cell> {
        return when (viewTypeValues[viewType]) {
            ViewType.SUBRECYCLER -> SubListViewHolder(
                layoutInflater,
                parent,
                listener
            )
            ViewType.CHAPTER -> ChapterViewHolder(
                layoutInflater,
                parent,
                listener
            )
        }
    }

    override fun onBindViewHolder(holder: AbsViewHolder<Cell>, position: Int) {
        holder.bind(content[position])
    }

    override fun getItemViewType(position: Int): Int {
        return content[position].viewType.ordinal
    }

    override fun getItemCount(): Int = content.size

    enum class ViewType {
        SUBRECYCLER,
        CHAPTER,
    }
}

interface DatabaseListener {
    fun onSubRecyclerTopicClick()
    fun onChapterClick()
    fun onSubRecyclerCellClick()
}