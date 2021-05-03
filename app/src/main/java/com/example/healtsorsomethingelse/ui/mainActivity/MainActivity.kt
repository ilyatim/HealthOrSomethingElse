package com.example.healtsorsomethingelse.ui.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healtsorsomethingelse.R
import com.example.healtsorsomethingelse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
}