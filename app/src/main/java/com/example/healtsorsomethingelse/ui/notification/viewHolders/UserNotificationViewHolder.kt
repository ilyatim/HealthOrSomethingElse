package com.example.healtsorsomethingelse.ui.notification.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.databinding.ItemNotificationUserBinding
import com.example.healtsorsomethingelse.ui.notification.AbsNotificationViewHolder
import com.example.healtsorsomethingelse.utils.TimeUtils

class UserNotificationViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val binding: ItemNotificationUserBinding =
        ItemNotificationUserBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsNotificationViewHolder(binding.root) {

    override fun bind(cell: Notifications) {
        cell as UserNotification

        binding.topicTextView.text = cell.userName
        binding.textViewHolder.text = cell.topic
        binding.dateTextView.text = TimeUtils.parseNotificationDate(cell.date)

        //Handle Image
    }
}