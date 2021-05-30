package com.example.mooncascadetest.presentation.placesandwinds

import android.util.Log
import com.example.mooncascadetest.domain.placesandwinds.PlacesAndWindsInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import javax.inject.Inject

class PlacesAndWindsViewModel @Inject constructor(
    private val placesAndWindsInteractor: PlacesAndWindsInteractor
) : BaseViewModel() {

    init {
        Log.d("SSS", "PlacesAndWindsViewModel init")
    }

}