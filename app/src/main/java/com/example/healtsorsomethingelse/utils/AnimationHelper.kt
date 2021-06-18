package com.example.healtsorsomethingelse.utils

import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimationHelperImpl @Inject constructor() : AnimationHelper {

    override fun setOffSetListener(appBarLayout: AppBarLayout) {
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
}

interface AnimationHelper {
    fun setOffSetListener(appBarLayout: AppBarLayout)
}