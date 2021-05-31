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
            .typeInt(
                arguments?.getInt(ARG_TYPE_INT) ?: throw IllegalStateException("Wrong or not found argument: typeId")
            )
            .id(
                arguments?.getLong(ARG_ID) ?: throw IllegalStateException("Wrong or not found argument: id")
            )
            .build()
            .inject(this)
    }

    override fun provideToolbarSubTitleStringRes() = R.string.place_or_wind

    companion object {
        private const val ARG_TYPE_INT = "ARG_TYPE_INT"
        private const val ARG_ID = "ARG_ID"

        fun newInstance(typeInt: Int, id: Long) = PlaceOrWindFragment().apply {
            arguments = bundleOf(ARG_TYPE_INT to typeInt, ARG_ID to id)
        }
    }
}