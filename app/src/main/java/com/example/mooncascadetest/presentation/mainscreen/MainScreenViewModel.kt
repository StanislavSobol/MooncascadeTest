package com.example.mooncascadetest.presentation.mainscreen

import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.presentation.BaseViewModel
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val mainScreenInteractor: MainScreenInteractor) :
    BaseViewModel() {

}