package com.example.mooncascadetest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooncascadetest.tools.EMPTY_STRING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    private val _showProgressLiveData = MutableLiveData<Boolean>()

    /**
     * Shows or hides a loading status. True - loading status, otherwise - normal one.
     */
    val showProgressLiveData: LiveData<Boolean>
        get() = _showProgressLiveData

    private val _showErrorLiveData = MutableLiveData<String>()

    /**
     * Shows an error status. If the parameter is empty, the error status is to be changed to normal one
     */
    val showErrorLiveData: LiveData<String>
        get() = _showErrorLiveData

    fun launchWithProgressInDispatchersIO(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _showProgressLiveData.postValue(true)
                withContext(Dispatchers.IO) {
                    block.invoke()
                }
                _showProgressLiveData.postValue(false)
                _showErrorLiveData.postValue(EMPTY_STRING)
            } catch (e: Exception) {
                _showErrorLiveData.postValue(e.message)
            }
        }
    }
}