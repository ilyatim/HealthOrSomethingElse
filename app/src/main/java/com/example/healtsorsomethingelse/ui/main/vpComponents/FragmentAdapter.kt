package com.example.healtsorsomethingelse.ui.main.vpComponents

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.healtsorsomethingelse.ui.main.fragments.WorkoutFragment
import com.example.healtsorsomethingelse.ui.main.fragments.FoodFragment

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val NUM_PAGES = 3

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> WorkoutFragment.newInstance()
            else -> FoodFragment.newInstance()
        }
    }
}