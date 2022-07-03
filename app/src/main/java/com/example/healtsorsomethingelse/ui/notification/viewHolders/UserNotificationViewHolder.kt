package com.example.healtsorsomethingelse.ui.notification.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.core.ui.AbsBindingViewHolder
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.databinding.ItemNotificationUserBinding
import com.example.healtsorsomethingelse.ui.notification.OnClickCallback
import com.example.healtsorsomethingelse.utils.TimeUtils

class UserNotificationViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val onClickCallback: OnClickCallback,
) : AbsBindingViewHolder<Notifications, ItemNotificationUserBinding>(
    ItemNotificationUserBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {

    fun getForegroundView(): View {
        return binding.viewForeground
    }

    fun getBackgroundView(): View {
        return binding.viewBackground
    }

    override fun bind(cell: Notifications) = withBinding {
        cell as UserNotification

        root.setOnClickListener {
            onClickCallback.onNotificationClick(cell.id, it)
        }

        topicTextView.text = cell.userName
        textViewHolder.text = cell.topic
        dateTextView.text = TimeUtils.parseNotificationDate(cell.date)

        Glide.with(userIconImageView)
            .load(cell.imageUrl)
            .placeholder(R.drawable.rounded_image_view_with_default_color)
            .circleCrop()
            .into(userIconImageView)
    }
}