package com.example.mooncascadetest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.ActivityMainBinding
import com.example.mooncascadetest.presentation.mainscreen.MainScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.toolbar))
        if (savedInstanceState != null) {
            hideStatus()
        } else {
            navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerLayout, MainScreenFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun hideStatus() {
        // TODO Gone
        binding.statusTextView.isVisible = false
    }
}