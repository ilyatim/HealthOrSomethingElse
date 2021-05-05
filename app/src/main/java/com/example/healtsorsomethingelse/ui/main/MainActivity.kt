package com.example.healtsorsomethingelse.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpController()
    }

    private fun setUpController() {
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        return true
    }
}
