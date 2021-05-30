package com.example.mooncascadetest.presentation.placesandwinds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.mooncascadetest.MApplication
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.FragmentMainScreenBinding
import com.example.mooncascadetest.di.DaggerPlacesAndWindsComponent
import com.example.mooncascadetest.presentation.BaseFragment
import com.example.mooncascadetest.presentation.FragmentType
import com.example.mooncascadetest.tools.ViewModelFactory
import java.util.*
import javax.inject.Inject

class PlacesAndWindsFragment : BaseFragment(FragmentType.Child, R.layout.fragment_main_screen) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PlacesAndWindsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PlacesAndWindsViewModel::class.java)
    }

    private lateinit var binding: FragmentMainScreenBinding

    // TODO get binding from the parent
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
//        val adapter =
//            MainScreenItemsAdapter().apply { placesAndWindsOnClick = { viewModel.onPlacesAndWindsClicked(it) } }
//        binding.recyclerView.adapter = adapter
//        viewModel.forecastLiveData.observe { adapter.setItems(it) }
//        viewModel.toPlacesAndWindsEvent.observe {
//            it.getContentIfNotHandled()?.let {
//                mainActivity.toPlacesAndWindsScreen(it)
//            }
//        }
    }

    override fun injectDependencies() {
        DaggerPlacesAndWindsComponent.builder()
            .appComponent(MApplication.getAppComponent())
            .build()
            .inject(this)
    }

    override fun provideToolbarSubTitleStringRes() = R.string.places_and_winds

    companion object {
        private const val ARG_DATE = "ARG_DATE"

        fun newInstance(date: Date) = PlacesAndWindsFragment().apply {
            arguments = bundleOf(ARG_DATE to date)
        }
    }
}