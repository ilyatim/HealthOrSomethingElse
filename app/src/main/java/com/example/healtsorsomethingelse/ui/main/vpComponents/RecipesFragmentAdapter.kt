package com.example.healtsorsomethingelse.ui.main.vpComponents

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.healtsorsomethingelse.ui.main.fragments.AllRecipesFragment
import com.example.healtsorsomethingelse.ui.main.fragments.FavoriteRecipesFragment
import com.example.healtsorsomethingelse.ui.main.fragments.VegetarianRecipesFragment

class RecipesFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val NUM_PAGES = 3

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllRecipesFragment.newInstance()
            1 -> FavoriteRecipesFragment.newInstance()
            else -> VegetarianRecipesFragment.newInstance()
        }
    }
}