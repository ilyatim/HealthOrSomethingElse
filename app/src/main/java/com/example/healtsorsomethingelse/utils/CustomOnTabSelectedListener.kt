package com.example.healtsorsomethingelse.utils

import com.google.android.material.tabs.TabLayout

interface CustomOnTabSelectedListener : TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?)
    override fun onTabReselected(tab: TabLayout.Tab?) {}
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
}