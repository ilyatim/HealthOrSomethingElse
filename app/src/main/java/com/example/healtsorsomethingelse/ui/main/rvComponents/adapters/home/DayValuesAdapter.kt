package com.example.healtsorsomethingelse.ui.main.rvComponents.adapters.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.AbsMultipleAdapter
import com.example.core.ui.AbsViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.ui.main.rvComponents.viewHolders.home.*

class DayValuesAdapter(
    private val layoutInflater: LayoutInflater,
    list: MutableList<DayStatisticsItem>
) : AbsMultipleAdapter<DayStatisticsItem, AbsViewHolder<DayStatisticsItem>, DayValuesAdapter.ViewType>(
    list
) {
    private val viewTypeValues = ViewType.values()
    override val DayStatisticsItem.viewType: ViewType
        get() = when (this) {
            is DayStatisticsItem.Calories -> ViewType.CALORIES
            is DayStatisticsItem.Distance -> ViewType.DISTANCE
            is DayStatisticsItem.Steps -> ViewType.STEPS
            is DayStatisticsItem.Workout -> ViewType.WORKOUT
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<DayStatisticsItem> =
        when(viewTypeValues[viewType]) {
            ViewType.STEPS -> DayStepsViewHolder(layoutInflater, parent)
            ViewType.CALORIES -> DayCaloriesViewHolder(layoutInflater, parent)
            ViewType.DISTANCE -> DayDistanceViewHolder(layoutInflater, parent)
            ViewType.WORKOUT -> DayWorkoutViewHolder(layoutInflater, parent)
        }

    enum class ViewType {
        STEPS,
        CALORIES,
        DISTANCE,
        WORKOUT
    }

    override fun getDiffUtils(
        oldList: List<DayStatisticsItem>,
        newList: List<DayStatisticsItem>,
    ): DiffUtil.Callback = DiffUtilImpl(oldList, newList)
}