package com.example.healtsorsomethingelse.ui.notification.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.ui.AbsBindingViewHolder
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.databinding.ItemNotificationTopicBinding

class TopicNotificationViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
) : AbsBindingViewHolder<Notifications, ItemNotificationTopicBinding>(
    ItemNotificationTopicBinding.inflate(
        layoutInflater,
        parent,
        false
    )
) {
    override fun bind(cell: Notifications) = withBinding {
        cell as NotificationTopic
        topicTextView.text = cell.text
    }
}