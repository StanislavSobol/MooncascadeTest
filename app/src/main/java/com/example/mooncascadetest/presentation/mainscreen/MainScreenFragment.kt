package com.example.mooncascadetest.presentation.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mooncascadetest.MApplication
import com.example.mooncascadetest.R
import com.example.mooncascadetest.di.DaggerMainScreenComponent
import com.example.mooncascadetest.presentation.BaseFragment
import com.example.mooncascadetest.presentation.FragmentType
import com.example.mooncascadetest.tools.ViewModelFactory
import javax.inject.Inject

class MainScreenFragment : BaseFragment(FragmentType.Main, R.layout.fragment_main_screen) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainScreenViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainScreenViewModel::class.java)
    }

    override fun injectDependencies() {
        DaggerMainScreenComponent.builder()
            .appComponent(MApplication.getAppComponent())
            .build()
            .inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.vehiclesLiveData.observe {
            Log.d("SSS", "Start it = ${it.size}")
            it.forEach { item ->
                Log.d("SSS", "item = $item")
            }
        }
    }

    companion object {
        fun newInstance() = MainScreenFragment()
    }
}