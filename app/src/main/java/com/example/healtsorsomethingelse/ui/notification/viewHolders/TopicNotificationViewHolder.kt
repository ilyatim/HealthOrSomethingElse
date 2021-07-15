package com.example.healtsorsomethingelse.ui.notification.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.notification.NotificationTopic
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.databinding.ItemNotificationTopicBinding
import com.example.healtsorsomethingelse.utils.AbsViewHolder

class TopicNotificationViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemNotificationTopicBinding =
        ItemNotificationTopicBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Notifications>(binding.root) {
    override fun bind(cell: Notifications) {
        cell as NotificationTopic
        binding.topicTextView.text = cell.text
    }
}