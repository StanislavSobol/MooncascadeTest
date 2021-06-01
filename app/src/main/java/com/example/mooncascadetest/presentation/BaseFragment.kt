package com.example.mooncascadetest.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mooncascadetest.R

/**
 * Class to store all common features for diff. classes placed on MainActivity.
 */
abstract class BaseFragment(private val fragmentType: FragmentType) : Fragment() {

    protected val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.supportActionBar?.let {
            when (fragmentType) {
                FragmentType.Main -> {
                    it.setDisplayHomeAsUpEnabled(false)
                }
                FragmentType.Child -> {
                    it.setHomeAsUpIndicator(R.drawable.ic_arrow_mc_yellow_24dp)
                    it.setDisplayHomeAsUpEnabled(true)
                }
            }
        }

        mainActivity.setToolbarSubTitle(provideToolbarSubTitleStringRes())

        injectDependencies()
    }

    protected abstract fun injectDependencies()

    @StringRes
    protected abstract fun provideToolbarSubTitleStringRes(): Int

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { block.invoke(it) })
    }
}

enum class FragmentType {
    Main, Child
}