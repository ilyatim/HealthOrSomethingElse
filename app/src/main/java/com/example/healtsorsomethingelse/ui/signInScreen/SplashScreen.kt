package com.example.healtsorsomethingelse.ui.signInScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.ActivitySplashScreenBinding
import com.example.healtsorsomethingelse.ui.mainScreen.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private var account: GoogleSignInAccount? = null

    private val googleSingInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.d("Sometag", it.resultCode.toString())
        if (it.resultCode == Activity.RESULT_OK) {
            startMainActivity(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setStatusBarColor()
        setContentView(binding.root)

        binding.splashScreenSignInButton.setSize(SignInButton.SIZE_WIDE)

        if (checkAccount()) {
            startAnimation(this)
        } else {
            setClick(binding.splashScreenSignInButton)
            startAnimation(this)
        }
    }

    private fun startMainActivity(context: Context) {
        startActivity(Intent(
            context,
            MainActivity::class.java
        ).addFlags(FLAG_ACTIVITY_SINGLE_TOP))
        this.finish()
    }

    override fun onStart() {
        super.onStart()
        account = GoogleSignIn.getLastSignedInAccount(this)
    }

    private fun setClick(button: SignInButton) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSingInClient = GoogleSignIn.getClient(this, gso)
        button.setOnClickListener {
            googleSingInLauncher.launch(mGoogleSingInClient.signInIntent)
        }
    }

    private fun checkAccount(): Boolean {
        return account != null
    }

    private fun setStatusBarColor() {
        this.window?.apply {
            decorView.systemUiVisibility = 0
            statusBarColor = getColor(R.color.basicPurpleColorBackground)
        }
    }

    private fun startAnimation(context: Context) {
        val animForImageView = AnimationUtils.loadAnimation(
            context,
            R.anim.dragging_up_main_icon_splash_screen
        ).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    binding.splashScreenSignInButton.animate()
                        .alpha(1f)
                        .setDuration(500)
                        .setListener(null)
                }

                override fun onAnimationStart(p0: Animation?) {
                    binding.splashScreenSignInButton.alpha = 0f
                }
            })
            startOffset = 1000
        }
        binding.splashScreenImageView.startAnimation(animForImageView)
    }
}