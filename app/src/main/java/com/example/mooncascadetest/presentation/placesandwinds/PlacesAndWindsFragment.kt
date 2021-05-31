package com.example.mooncascadetest.presentation.placesandwinds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.mooncascadetest.MApplication
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.FragmentRecyclerViewBinding
import com.example.mooncascadetest.di.DaggerPlacesAndWindsComponent
import com.example.mooncascadetest.presentation.BaseFragment
import com.example.mooncascadetest.presentation.FragmentType
import com.example.mooncascadetest.presentation.placesandwinds.model.PlaceAndWindsItemDelegateType
import com.example.mooncascadetest.tools.ViewModelFactory
import java.util.*
import javax.inject.Inject

class PlacesAndWindsFragment : BaseFragment(FragmentType.Child) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PlacesAndWindsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PlacesAndWindsViewModel::class.java)
    }

    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PlacesAndWindsAdapter().apply { itemOnClick = { viewModel.onItemClicked(it) } }
        binding.recyclerView.adapter = adapter
        viewModel.placesAndWindsLiveData.observe { adapter.setItems(it) }
        viewModel.toPlaceOrWindDetailEvent.observe {
            it.getContentIfNotHandled()?.let { item ->
                mainActivity.toPlaceOrWIndScreen(item.type == PlaceAndWindsItemDelegateType.PLACE, item.id)
            }
        }
    }

    override fun injectDependencies() {
        DaggerPlacesAndWindsComponent.builder()
            .appComponent(MApplication.getAppComponent())
            .date(
                Date().apply {
                    time = arguments?.getLong(ARG_DATE)
                        ?: throw IllegalStateException("Wrong or not found argument: date")
                }
            )
            .build()
            .inject(this)
    }

    override fun provideToolbarSubTitleStringRes() = R.string.places_and_winds

    companion object {
        private const val ARG_DATE = "ARG_DATE"

        fun newInstance(date: Date) = PlacesAndWindsFragment().apply {
            arguments = bundleOf(ARG_DATE to date.time)
        }
    }
}