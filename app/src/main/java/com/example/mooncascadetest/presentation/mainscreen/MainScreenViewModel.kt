package com.example.mooncascadetest.presentation.mainscreen

import android.util.Log
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val mainScreenInteractor: MainScreenInteractor) :
    BaseViewModel() {

    init {
        launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone = true) {
            Log.d("SSS", "MainScreenViewModel init")
            mainScreenInteractor.requestForecast()
        }
    }

}