package com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.home.DayStatisticsItem
import com.example.healtsorsomethingelse.ui.mainActivity.rvComponents.viewHolders.*

class DayValuesAdapter(
    private val layoutInflater: LayoutInflater,
    private val list: MutableList<DayStatisticsItem>
) : RecyclerView.Adapter<AbsDayViewHolder>() {

    private val viewTypeValues = ViewType.values()
    private val DayStatisticsItem.viewType: ViewType
        get() = when (this) {
            is DayStatisticsItem.Calories -> ViewType.CALORIES
            is DayStatisticsItem.Distance -> ViewType.DISTANCE
            is DayStatisticsItem.Steps -> ViewType.STEPS
            is DayStatisticsItem.Workout -> ViewType.WORKOUT
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsDayViewHolder {
        return when(viewTypeValues[viewType]) {
            ViewType.STEPS -> DayStepsViewHolder(layoutInflater, parent)
            ViewType.CALORIES -> DayCaloriesViewHolder(layoutInflater, parent)
            ViewType.DISTANCE -> DayDistanceViewHolder(layoutInflater, parent)
            ViewType.WORKOUT -> DayWorkoutViewHolder(layoutInflater, parent)
        }
    }

    override fun onBindViewHolder(holder: AbsDayViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType.ordinal
    }
    override fun getItemCount(): Int = list.size

    private enum class ViewType {
        STEPS,
        CALORIES,
        DISTANCE,
        WORKOUT
    }
}