package com.example.mooncascadetest.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.ActivityMainBinding
import com.example.mooncascadetest.presentation.mainscreen.MainScreenFragment
import com.example.mooncascadetest.presentation.placeorwind.PlaceOrWindFragment
import com.example.mooncascadetest.presentation.placesandwinds.PlacesAndWindsFragment
import java.util.*

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
            toMainScreen()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            supportFragmentManager.popBackStack()
            true
        } else {
            false
        }
    }

    fun setToolbarSubTitle(@StringRes text: Int) {
        binding.subtitleTextView.setText(text)
    }

    fun toPlacesAndWindsScreen(date: Date) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.containerLayout, PlacesAndWindsFragment.newInstance(date))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun toPlaceOrWIndScreen(typeInt: Int, id: Long) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.containerLayout, PlaceOrWindFragment.newInstance(typeInt, id))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun toMainScreen() {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.containerLayout, MainScreenFragment.newInstance())
            .commitAllowingStateLoss()
    }

    private fun hideStatus() {
        // TODO Gone
        binding.statusTextView.isVisible = false
    }
}