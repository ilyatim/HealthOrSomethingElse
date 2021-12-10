package com.example.healtsorsomethingelse.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.healtsorsomethingelse.databinding.ActivityNotificationInfoBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class NotificationInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        //findViewById<View>(android.R.id.content).transitionName = "notification_element_container"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
        }
        //Animation for exit from activity
        /*window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }*/
        //
        binding = ActivityNotificationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {

        const val STRING_ID: String = "notification_id"

        fun startActivity(activity: Activity, id: String, startView: View) {
            val intent = Intent(activity, NotificationInfoActivity::class.java).apply {
                putExtra(STRING_ID, id)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                startView,
            "notification_element_container"
            )
            activity.startActivity(intent, options.toBundle())
        }
    }
}