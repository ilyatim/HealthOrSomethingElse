package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.data.home.Statistics
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders.AbsViewHolder
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders.WeekStatViewHolder
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders.AdviceViewHolder
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders.DayStatViewHolder
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders.SleepViewHolder

class Adapter(
    private val layoutInflater: LayoutInflater,
    private val list: MutableList<Statistics>
) : RecyclerView.Adapter<AbsViewHolder>() {

    private val viewTypeValues = ViewType.values()
    private val Statistics.viewType: ViewType
        get() = when (this@viewType) {
            is Statistics.Advice -> ViewType.ADVICE
            is Statistics.DayStatistics -> ViewType.DAY
            is Statistics.SleepStatistics -> ViewType.SLEEP
            is Statistics.WeekStatistics -> ViewType.WEEK
        }
    private lateinit var diffUtil: DiffUtilImpl<Statistics>

    fun updateList(newList: List<Statistics>) {
        diffUtil = DiffUtilImpl(list, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        diffUtilResult.dispatchUpdatesTo(this)

        this.list.clear()
        this.list.addAll(newList)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder {
        return when(viewTypeValues[viewType]) {
            ViewType.ADVICE -> AdviceViewHolder(layoutInflater, parent)
            ViewType.DAY -> DayStatViewHolder(layoutInflater, parent)
            ViewType.WEEK -> WeekStatViewHolder(layoutInflater, parent)
            ViewType.SLEEP -> SleepViewHolder(layoutInflater, parent)
        }
    }

    override fun onBindViewHolder(holder: AbsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    private enum class ViewType {
        ADVICE,
        DAY,
        WEEK,
        SLEEP
    }
}