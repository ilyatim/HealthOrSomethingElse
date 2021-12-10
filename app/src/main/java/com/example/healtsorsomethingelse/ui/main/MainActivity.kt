package com.example.healtsorsomethingelse.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.ActivityMainBinding
import com.example.healtsorsomethingelse.utils.LogUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val TAG: String = "MainActivityTag"
    private lateinit var binding: ActivityMainBinding
    private var account: GoogleSignInAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpController()
    }

    private val googleSingInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        LogUtils.log("google sign in result ${it.resultCode}", Log.INFO, TAG)
        //TODO("добавить логику обработки проспуска авторизации")
        if (it.resultCode == Activity.RESULT_OK) {
            //startMainActivity(this)
        }
    }

    override fun onStart() {
        super.onStart()
        account = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null) {
            showGoogleSingInDialog()
        }
    }

    private fun showGoogleSingInDialog() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSingInClient = GoogleSignIn.getClient(this, gso)
        googleSingInLauncher.launch(mGoogleSingInClient.signInIntent)
    }

    private fun setUpController() {
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true
    }
}
