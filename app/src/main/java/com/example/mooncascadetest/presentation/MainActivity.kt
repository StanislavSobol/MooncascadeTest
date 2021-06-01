package com.example.mooncascadetest.presentation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
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
        animate()
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.containerLayout, PlacesAndWindsFragment.newInstance(date))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun toPlaceOrWIndScreen(isPlace: Boolean, id: Long) {
        animate()
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .replace(R.id.containerLayout, PlaceOrWindFragment.newInstance(isPlace, id))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun toMainScreen() {
        animate()
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

    private fun animate() {
        binding.titleTextView.translationX = getScreenWidth().toFloat()
        binding.titleTextView.alpha = 0f
        binding.subtitleTextView.translationX = getScreenWidth().toFloat()
        binding.subtitleTextView.alpha = 0f

        AnimatorSet().apply {
            interpolator = AccelerateInterpolator()
            playTogether(
                binding.titleTextView.createTranslationXAnimation(
                    value = ANIM_X_END_POINT,
                    duration = ANIM_TITLE_DURATION_MILLIS
                ),
                binding.titleTextView.createAlphaAnimation(
                    value = ANIM_ALPHA_END_POINT,
                    duration = ANIM_TITLE_DURATION_MILLIS
                ),
                binding.subtitleTextView.createTranslationXAnimation(
                    value = ANIM_X_END_POINT,
                    duration = ANIM_SUBTITLE_DURATION_MILLIS,
                    startDelay = ANIM_SUBTITLE_DELAY_MILLIS
                ),
                binding.subtitleTextView.createAlphaAnimation(
                    value = ANIM_ALPHA_END_POINT,
                    duration = ANIM_SUBTITLE_DURATION_MILLIS,
                    startDelay = ANIM_SUBTITLE_DELAY_MILLIS
                )
            )
        }.start()
    }

    private fun View.createTranslationXAnimation(value: Float, duration: Long, startDelay: Long = 0L): ObjectAnimator {
        return ObjectAnimator.ofFloat(this, View.TRANSLATION_X, value).apply {
            this.duration = duration
            this.startDelay = startDelay
        }
    }

    private fun View.createAlphaAnimation(value: Float, duration: Long, startDelay: Long = 0L): ObjectAnimator {
        return ObjectAnimator.ofFloat(this, View.ALPHA, value).apply {
            this.duration = duration
            this.startDelay = startDelay
        }
    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    companion object {
        private const val ANIM_X_END_POINT = 0f
        private const val ANIM_ALPHA_END_POINT = 1f
        private const val ANIM_TITLE_DURATION_MILLIS = 1000L
        private const val ANIM_SUBTITLE_DURATION_MILLIS = 1200L
        private const val ANIM_SUBTITLE_DELAY_MILLIS = 300L
    }
}