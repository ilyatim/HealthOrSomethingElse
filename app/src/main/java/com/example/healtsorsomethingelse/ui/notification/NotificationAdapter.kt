package com.example.healtsorsomethingelse.ui.notification

import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.AbsMultipleAdapter
import com.example.core.ui.AbsViewHolder
import com.example.core.utils.DiffUtilImpl
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.ui.notification.viewHolders.TopicNotificationViewHolder
import com.example.healtsorsomethingelse.ui.notification.viewHolders.UserNotificationViewHolder
import com.example.healtsorsomethingelse.utils.notifications.OnSwipeCallback
import com.example.healtsorsomethingelse.utils.notifications.UserNotificationSimpleCallback

class NotificationAdapter(
    private val layoutInflater: LayoutInflater,
    list: MutableList<Notifications>,
    private val callback: OnSwipeCallback,
    private val onClickCallback: OnClickCallback
) : AbsMultipleAdapter<Notifications, AbsViewHolder<Notifications>, NotificationAdapter.ViewType>(
    list
) {

    private val viewTypeValues = ViewType.values()
    override val Notifications.viewType: ViewType
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsViewHolder<Notifications> {
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

    override fun getDiffUtils(
        oldList: List<Notifications>,
        newList: List<Notifications>,
    ): DiffUtil.Callback = DiffUtilImpl(oldList, newList)

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

fun interface OnClickCallback {
    fun onNotificationClick(notificationId: String, view: View)
}


