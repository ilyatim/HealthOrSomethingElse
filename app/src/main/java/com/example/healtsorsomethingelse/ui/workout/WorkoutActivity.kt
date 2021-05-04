package com.example.healtsorsomethingelse.ui.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.ActivityWorkoutBinding
import com.example.healtsorsomethingelse.utils.AppBarStateChangeListener
import com.google.android.material.appbar.AppBarLayout

class WorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutBinding

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
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setOffSetListener(binding.appBarLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}