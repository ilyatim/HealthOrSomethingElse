package com.example.healtsorsomethingelse.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.ActivityNotificationBinding
import com.example.healtsorsomethingelse.utils.AppBarStateChangeListener
import com.google.android.material.appbar.AppBarLayout

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding

    private fun setOffSetListener(appBarLayout: AppBarLayout) {
        val provider = appBarLayout.outlineProvider
        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                when (state) {
                    State.COLLAPSED -> {
                        appBarLayout.outlineProvider = provider
                    }
                    State.EXPANDED -> {
                        appBarLayout.outlineProvider = null
                    }
                    State.IDLE -> {}
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        setOffSetListener(binding.appBarLayout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d("Sometag", "title - ${supportActionBar?.title}, coll title - ${binding.collapsingToolbar.title}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}