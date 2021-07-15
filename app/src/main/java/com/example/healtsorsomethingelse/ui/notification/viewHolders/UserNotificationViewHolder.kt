package com.example.healtsorsomethingelse.ui.notification.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.data.notification.Notifications
import com.example.healtsorsomethingelse.data.notification.UserNotification
import com.example.healtsorsomethingelse.databinding.ItemNotificationUserBinding
import com.example.healtsorsomethingelse.ui.notification.OnClickCallback
import com.example.healtsorsomethingelse.utils.AbsViewHolder
import com.example.healtsorsomethingelse.utils.TimeUtils

class UserNotificationViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    private val onClickCallback: OnClickCallback,
    private val binding: ItemNotificationUserBinding =
        ItemNotificationUserBinding.inflate(
            layoutInflater,
            parent,
            false
        )
) : AbsViewHolder<Notifications>(binding.root) {

    fun getForegroundView(): View {
        return binding.viewForeground
    }

    fun getBackgroundView(): View {
        return binding.viewBackground
    }

    override fun bind(cell: Notifications) {
        cell as UserNotification

        binding.root.setOnClickListener {
            onClickCallback.onNotificationClick(cell.id, it)
        }

        binding.topicTextView.text = cell.userName
        binding.textViewHolder.text = cell.topic
        binding.dateTextView.text = TimeUtils.parseNotificationDate(cell.date)

        Glide.with(binding.userIconImageView)
            .load(cell.imageUrl)
            .placeholder(R.drawable.rounded_image_view_with_default_color)
            .circleCrop()
            .into(binding.userIconImageView)
    }
}