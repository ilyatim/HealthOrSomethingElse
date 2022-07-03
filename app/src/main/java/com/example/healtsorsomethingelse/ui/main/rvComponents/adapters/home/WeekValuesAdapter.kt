package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.core.ui.AbsMultipleAdapter
import com.example.core.ui.AbsViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.WeekDistanceViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.WeekStepsViewHolder
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.WeekWorkoutViewHolder

class WeekValuesAdapter(
    private val layoutInflater: LayoutInflater,
    list: MutableList<WeekStatisticsItem>
) : AbsMultipleAdapter<WeekStatisticsItem, AbsViewHolder<WeekStatisticsItem>, WeekValuesAdapter.ViewType>(
    list
) {

    private val viewTypeValues = ViewType.values()
    override val WeekStatisticsItem.viewType: ViewType
        get() = when (this) {
            is WeekStatisticsItem.Distance -> ViewType.DISTANCE
            is WeekStatisticsItem.Steps -> ViewType.STEPS
            is WeekStatisticsItem.Workout -> ViewType.WORKOUT
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<WeekStatisticsItem> =
        when (viewTypeValues[viewType]) {
            ViewType.STEPS -> WeekStepsViewHolder(layoutInflater, parent)
            ViewType.DISTANCE -> WeekDistanceViewHolder(layoutInflater, parent)
            ViewType.WORKOUT -> WeekWorkoutViewHolder(layoutInflater, parent)
        }

    override fun getDiffUtils(
        oldList: List<WeekStatisticsItem>,
        newList: List<WeekStatisticsItem>,
    ): DiffUtil.Callback = DiffUtilImpl(oldList, newList)

    enum class ViewType {
        STEPS,
        DISTANCE,
        WORKOUT
    }
}