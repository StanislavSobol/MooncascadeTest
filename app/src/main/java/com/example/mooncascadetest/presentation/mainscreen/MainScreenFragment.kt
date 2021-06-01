package com.example.mooncascadetest.presentation.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mooncascadetest.MApplication
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.FragmentRecyclerViewBinding
import com.example.mooncascadetest.di.DaggerMainScreenComponent
import com.example.mooncascadetest.presentation.BaseFragment
import com.example.mooncascadetest.presentation.FragmentType
import com.example.mooncascadetest.tools.ViewModelFactory
import javax.inject.Inject

class MainScreenFragment : BaseFragment(FragmentType.Main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainScreenViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainScreenViewModel::class.java)
    }

    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainScreenItemsAdapter().apply {
            placesAndWindsOnClick = { viewModel.onPlacesAndWindsClicked(it) }
        }
        binding.recyclerView.adapter = adapter
        viewModel.forecastLiveData.observe { adapter.setItems(it) }
        viewModel.toPlacesAndWindsEvent.observe {
            it.getContentIfNotHandled()?.let {
                mainActivity.toPlacesAndWindsScreen(it)
            }
        }
        viewModel.showProgressLiveData.observe { mainActivity.showLoadingStatus(it) }
        viewModel.showErrorLiveData.observe { mainActivity.showErrorStatus(it) }
    }

    override fun injectDependencies() {
        DaggerMainScreenComponent.builder()
            .appComponent(MApplication.getAppComponent())
            .build()
            .inject(this)
    }

    override fun provideToolbarSubTitleStringRes() = R.string.current_forecast

    companion object {
        fun newInstance() = MainScreenFragment()
    }
}