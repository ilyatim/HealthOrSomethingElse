package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.core.ui.AbsAdapter
import com.example.core.ui.AbsViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.AdviceViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.DayStatViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.SleepViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.WeekStatViewHolder

class Adapter(
    private val layoutInflater: LayoutInflater,
    list: MutableList<Statistics>
) : AbsAdapter<Statistics, AbsViewHolder<Statistics>, Adapter.ViewType>(list) {

    private val viewTypeValues = ViewType.values()

    override val Statistics.viewType: ViewType
        get() = when (this@viewType) {
            is Statistics.Advice -> ViewType.ADVICE
            is Statistics.DayStatistics -> ViewType.DAY
            is Statistics.SleepStatistics -> ViewType.SLEEP
            is Statistics.WeekStatistics -> ViewType.WEEK
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<Statistics> {
        return when(viewTypeValues[viewType]) {
            ViewType.ADVICE -> AdviceViewHolder(layoutInflater, parent)
            ViewType.DAY -> DayStatViewHolder(layoutInflater, parent)
            ViewType.WEEK -> WeekStatViewHolder(layoutInflater, parent)
            ViewType.SLEEP -> SleepViewHolder(layoutInflater, parent)
        }
    }

    override fun getDiffUtils(
        oldList: List<Statistics>,
        newList: List<Statistics>,
    ): DiffUtil.Callback {
        return DiffUtilImpl(oldList, newList)
    }

    enum class ViewType {
        ADVICE,
        DAY,
        WEEK,
        SLEEP
    }
}