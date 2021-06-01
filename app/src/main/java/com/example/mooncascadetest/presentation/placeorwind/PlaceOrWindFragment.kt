package com.example.mooncascadetest.presentation.placeorwind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.mooncascadetest.MApplication
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.FragmentPlaceOrWindBinding
import com.example.mooncascadetest.di.DaggerPlaceOrWindComponent
import com.example.mooncascadetest.presentation.BaseFragment
import com.example.mooncascadetest.presentation.FragmentType
import com.example.mooncascadetest.tools.ViewModelFactory
import com.example.mooncascadetest.tools.notFoundArgumentExMessage
import com.example.mooncascadetest.tools.setTextWithVisibility
import javax.inject.Inject

class PlaceOrWindFragment : BaseFragment(FragmentType.Child) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PlaceOrWindViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PlaceOrWindViewModel::class.java)
    }

    private lateinit var binding: FragmentPlaceOrWindBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlaceOrWindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.placeOrWindLiveData.observe {
            with(binding) {
                nameTextView.setTextWithVisibility(it.name)
                imageView.setImageResource(
                    if (it.isPlace) {
                        R.drawable.ic_baseline_location_city_24
                    } else {
                        R.drawable.ic_baseline_call_made_24
                    }
                )
                dayDescrTextView.setTextWithVisibility(it.dayDescr)
                dayRangeTextView.setTextWithVisibility(it.dayRange)
                nightDescrTextView.setTextWithVisibility(it.nightDescr)
                nightRangeTextView.setTextWithVisibility(it.nightRange)
            }
        }
    }

    override fun injectDependencies() {
        DaggerPlaceOrWindComponent.builder()
            .appComponent(MApplication.getAppComponent())
            .isPlace(
                arguments?.getBoolean(ARG_IS_PLACE) ?: throw IllegalStateException(
                    notFoundArgumentExMessage(ARG_IS_PLACE)
                )
            )
            .id(
                arguments?.getLong(ARG_ID) ?: throw IllegalStateException(
                    notFoundArgumentExMessage(ARG_ID)
                )
            )
            .build()
            .inject(this)
    }

    override fun provideToolbarSubTitleStringRes() = R.string.place_or_wind

    companion object {
        private const val ARG_IS_PLACE = "ARG_IS_PLACE"
        private const val ARG_ID = "ARG_ID"

        fun newInstance(isPlace: Boolean, id: Long) = PlaceOrWindFragment().apply {
            arguments = bundleOf(ARG_IS_PLACE to isPlace, ARG_ID to id)
        }
    }
}