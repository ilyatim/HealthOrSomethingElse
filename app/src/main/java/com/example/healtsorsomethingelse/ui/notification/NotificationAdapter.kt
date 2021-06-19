package com.example.healtsorsomethingelse.ui.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.extensions.ContextExtensions.showLongToast
import com.example.healtsorsomethingelse.ui.notification.viewHolders.TopicNotificationViewHolder
import com.example.healtsorsomethingelse.ui.notification.viewHolders.UserNotificationViewHolder
import com.example.healtsorsomethingelse.utils.DiffUtilImpl

class NotificationAdapter(
    private val layoutInflater: LayoutInflater,
    private val list: MutableList<Notifications>
) : RecyclerView.Adapter<AbsNotificationViewHolder>() {

    private lateinit var diffUtils: DiffUtilImpl<Notifications>
    private val viewTypeValues = ViewType.values()
    private val Notifications.viewType: ViewType
        get() = when (this) {
            is NotificationTopic -> ViewType.TOPIC
            is UserNotification -> ViewType.USER
        }


    private val itemTouchListener = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            layoutInflater.context.showLongToast("on move")
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            layoutInflater.context.showLongToast("on swiped")/*
            list.removeAt(viewHolder.absoluteAdapterPosition)
            notifyItemChanged(viewHolder.absoluteAdapterPosition)*/
            val newList = list
            newList.removeAt(viewHolder.bindingAdapterPosition)
            updateList(newList)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val itemTouchHelper = ItemTouchHelper(itemTouchListener)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        super.onAttachedToRecyclerView(recyclerView)
    }

    fun updateList(newList: List<Notifications>) {
        diffUtils = DiffUtilImpl(list, newList)
        val diffUtilsResult = DiffUtil.calculateDiff(diffUtils)
        diffUtilsResult.dispatchUpdatesTo(this)

        list.clear()
        list.addAll(newList)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsNotificationViewHolder {
        return when (viewTypeValues[viewType]) {
            ViewType.TOPIC -> {
                TopicNotificationViewHolder(
                    layoutInflater,
                    parent
                )
            }
            ViewType.USER -> {
                UserNotificationViewHolder(
                    layoutInflater,
                    parent
                )
            }
        }
    }

    override fun onBindViewHolder(holder: AbsNotificationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    enum class ViewType {
        TOPIC,
        USER,
    }
}

abstract class AbsNotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(cell: Notifications)
}

