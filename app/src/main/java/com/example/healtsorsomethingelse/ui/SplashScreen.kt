package com.example.healtsorsomethingelse.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.healtsorsomethingelse.R
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO change method
        this.window?.apply {
            decorView.systemUiVisibility = 0
            statusBarColor = getColor(R.color.basicPurpleColorBackground)
        }
        setContentView(R.layout.activity_splash_screen)
        splash_screen_sign_in_button.setSize(SignInButton.SIZE_WIDE)

        startAnimation(this)
    }

    private fun startAnimation(context: Context) {
        val animForImageView = AnimationUtils.loadAnimation(context,
            R.anim.dragging_up_main_icon_splash_screen
        )
        animForImageView.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                splash_screen_sign_in_button.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setListener(null)
            }

            override fun onAnimationStart(p0: Animation?) {
                splash_screen_sign_in_button.alpha = 0f
            }
        })
        splash_screen_imageView.startAnimation(animForImageView)
        animForImageView.startOffset = 1000
    }
    //TODO add action on the button "sign in"
}