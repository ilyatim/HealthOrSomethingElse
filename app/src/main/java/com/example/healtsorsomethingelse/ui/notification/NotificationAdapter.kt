package com.example.healtsorsomethingelse.ui.notification

import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.ui.notification.viewHolders.TopicNotificationViewHolder
import com.example.healtsorsomethingelse.ui.notification.viewHolders.UserNotificationViewHolder
import com.example.healtsorsomethingelse.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.utils.notifications.OnSwipeCallback
import com.example.healtsorsomethingelse.utils.notifications.UserNotificationSimpleCallback

class NotificationAdapter(
    private val layoutInflater: LayoutInflater,
    private val list: MutableList<Notifications>,
    private val callback: OnSwipeCallback,
    private val onClickCallback: OnClickCallback
) : RecyclerView.Adapter<AbsNotificationViewHolder>() {

    private lateinit var diffUtils: DiffUtilImpl<Notifications>
    private val viewTypeValues = ViewType.values()
    private val Notifications.viewType: ViewType
        get() = when (this) {
            is NotificationTopic -> ViewType.TOPIC
            is UserNotification -> ViewType.USER
        }

    private val itemTouchListener = SimpleCallBack(0, ItemTouchHelper.LEFT)

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
                    parent,
                    onClickCallback
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

    inner class SimpleCallBack(dragDirs: Int, moveDirs: Int) : UserNotificationSimpleCallback(dragDirs, moveDirs) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.bindingAdapterPosition
            if (position >= list.size) return

            val item = list[position]
            if (item is UserNotification) {
                callback.onSwipe(viewHolder.bindingAdapterPosition, item.id)
            }
        }
    }
}

abstract class AbsNotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(cell: Notifications)
}

fun interface OnClickCallback {
    fun onNotificationClick(notificationId: String, view: View)
}


