package com.example.mooncascadetest.presentation.mainscreen

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

    companion object {
        fun newInstance() = MainScreenFragment()
    }
}