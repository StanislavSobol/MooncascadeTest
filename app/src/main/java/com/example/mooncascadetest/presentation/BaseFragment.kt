package com.example.mooncascadetest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mooncascadetest.R

/**
 * Class to store all common features for diff. classes placed on MainActivity.
 *
 * No need to keep all constructor-passed parameters in arguments
 * because the default constructor is called from every child empty constructor every time the child fragment is recreated
 */
abstract class BaseFragment(
    private val fragmentType: FragmentType,
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.let {
                when (fragmentType) {
                    FragmentType.Main -> {
                        it.setDisplayHomeAsUpEnabled(false)
                    }
                    FragmentType.Child -> {
                        it.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
                        it.setDisplayHomeAsUpEnabled(true)
                    }
                }
            }
        }

        injectDependencies()
    }

    protected abstract fun injectDependencies()

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { block.invoke(it) })
    }
}

enum class FragmentType {
    Main, Child
}