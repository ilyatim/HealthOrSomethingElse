package com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.home.WeekStatisticsItem
import com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders.AbsWeekViewHolder
import com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders.WeekDistanceViewHolder
import com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders.WeekStepsViewHolder
import com.example.healtsorsomethingelse.ui.mainActvity.rvComponents.viewHolders.WeekWorkoutViewHolder

class WeekValuesAdapter(
    private val layoutInflater: LayoutInflater,
    private val list: MutableList<WeekStatisticsItem>
) : RecyclerView.Adapter<AbsWeekViewHolder>() {

    private val viewTypeValues = ViewType.values()
    private val WeekStatisticsItem.viewType: ViewType
        get() = when (this) {
            is WeekStatisticsItem.Distance -> ViewType.DISTANCE
            is WeekStatisticsItem.Steps -> ViewType.STEPS
            is WeekStatisticsItem.Workout -> ViewType.WORKOUT
        }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsWeekViewHolder {
        return when (viewTypeValues[viewType]) {
            ViewType.STEPS -> WeekStepsViewHolder(layoutInflater, parent)
            ViewType.DISTANCE -> WeekDistanceViewHolder(layoutInflater, parent)
            ViewType.WORKOUT -> WeekWorkoutViewHolder(layoutInflater, parent)
        }
    }

    override fun onBindViewHolder(holder: AbsWeekViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    private enum class ViewType {
        STEPS,
        DISTANCE,
        WORKOUT
    }
}