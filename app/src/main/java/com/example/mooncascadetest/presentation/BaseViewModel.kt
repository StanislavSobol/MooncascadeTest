package com.example.mooncascadetest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    private val _showProgressLiveData = MutableLiveData<Boolean>()
    val showProgressLiveData: LiveData<Boolean>
        get() = _showProgressLiveData

    private val _showErrorLiveData = MutableLiveData<String>()
    val showErrorLiveData: LiveData<String>
        get() = _showErrorLiveData

    protected var loading = false
        private set

    protected open fun showProgress() {
        loading = true
        _showProgressLiveData.postValue(true)
    }

    protected open fun hideProgress() {
        loading = false
        _showProgressLiveData.postValue(false)

    }

    fun launchWithProgressInDispatchersIO(hideLoadingStatusWhenDone: Boolean, block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                showProgress()
                withContext(Dispatchers.IO) {
                    block.invoke()
                }
                if (hideLoadingStatusWhenDone) {
                    hideProgress()
                }
            } catch (e: Exception) {
                _showErrorLiveData.postValue(e.message)
            }
        }
    }
}