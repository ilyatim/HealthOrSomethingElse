package com.example.healtsorsomethingelse.ui.main.vpComponents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.core.ui.AbsMultipleAdapter
import com.example.core.ui.AbsViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.data.database.mainScreen.UserDatabaseContent
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database.ChapterViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.database.SubListViewHolder
import com.example.healtsorsomethingelse.utils.UiUtils.setHolderBottomMargin
import com.example.healtsorsomethingelse.utils.UiUtils.setHolderTopMargin

class DatabaseAdapter(
    private val layoutInflater: LayoutInflater,
    list: MutableList<UserDatabaseContent>,
    private val listener: DatabaseListener,
) : AbsMultipleAdapter<UserDatabaseContent, AbsViewHolder<UserDatabaseContent>, DatabaseAdapter.ViewType>(
    list
) {

    private var topMargin: Int = 180

    private val viewTypeValues = ViewType.values()
    override val UserDatabaseContent.viewType: ViewType
        get() = when (this) {
            is UserDatabaseContent.Block -> ViewType.CHAPTER
            is UserDatabaseContent.ContentList -> ViewType.SUBRECYCLER
        }

    fun setTopMargin(margin: Int) {
        this.topMargin = margin
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<UserDatabaseContent> {
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

    override fun onViewDetachedFromWindow(holder: AbsViewHolder<UserDatabaseContent>) {
        (holder as? DatabaseViewHolder)?.saveState()
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: AbsViewHolder<UserDatabaseContent>, position: Int) {
        //Если верхний элемент, смещаем его на позицию searchbar
        if (position == 0) {
            setHolderTopMargin(holder, topMargin)
        } else {
            //Если идет 2 раза подряд блоки, делаем отступы между ними минимальными
            if (list[position] is UserDatabaseContent.Block
                && list[position - 1] is UserDatabaseContent.Block
            ) {
                setHolderTopMargin(holder, 0)
            }
            //Если позиция элемента последняя, делаем отступ снизу
            if (position == list.lastIndex) {
                if (holder is ChapterViewHolder) {
                    //По неведомой причине, последний элемент может иметь отступ сверху около 181
                    //Ставим отступ 0, чтобы избежать этой ситуации
                    setHolderTopMargin(holder, 0)
                    setHolderBottomMargin(holder, 20)
                } else if (holder is SubListViewHolder) {
                    setHolderBottomMargin(holder, 10)
                }
            }
        }

        holder.bind(list[position])
    }

    enum class ViewType {
        SUBRECYCLER,
        CHAPTER,
    }

    override fun getDiffUtils(
        oldList: List<UserDatabaseContent>,
        newList: List<UserDatabaseContent>,
    ): DiffUtil.Callback = DiffUtilImpl(oldList, newList)
}

interface DatabaseListener {
    fun onSubRecyclerTopicClick()
    fun onChapterClick()
    fun onSubRecyclerCellClick(itemId: Int)
}

interface DatabaseViewHolder {
    fun saveState()
}