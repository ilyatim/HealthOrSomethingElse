package com.example.healtsorsomethingelse.utils.notifications

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.ui.notification.viewHolders.UserNotificationViewHolder

abstract class UserNotificationSimpleCallback(dragDirs: Int,moveDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, moveDirs) {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return when (viewHolder) {
            is UserNotificationViewHolder -> {
                val swipeFlags = ItemTouchHelper.LEFT
                makeMovementFlags(0, swipeFlags)
            }
            else -> {
                makeMovementFlags(0, 0)
            }
        }
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let { _viewHolder ->
            if (_viewHolder is UserNotificationViewHolder) {
                getDefaultUIUtil().onSelected(_viewHolder.getForegroundView())
            }
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (viewHolder is UserNotificationViewHolder) {
            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                viewHolder.getForegroundView(),
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        viewHolder?.let { _viewHolder ->
            if (_viewHolder is UserNotificationViewHolder) {
                getDefaultUIUtil().onDrawOver(
                    c,
                    recyclerView,
                    _viewHolder.getForegroundView(),
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is UserNotificationViewHolder) {
            getDefaultUIUtil().clearView(viewHolder.getForegroundView())
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}