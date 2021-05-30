package com.example.mooncascadetest.di

import androidx.lifecycle.ViewModel
import com.example.mooncascadetest.domain.placesandwinds.PlacesAndWindsInteractor
import com.example.mooncascadetest.domain.placesandwinds.PlacesAndWindsInteractorImpl
import com.example.mooncascadetest.presentation.placesandwinds.PlacesAndWindsFragment
import com.example.mooncascadetest.presentation.placesandwinds.PlacesAndWindsViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PlacesAndWindsScope

@PlacesAndWindsScope
@Component(
    modules = [(PlacesAndWindsModule::class)],
    dependencies = [(AppComponent::class)]
)
interface PlacesAndWindsComponent {

    fun inject(fragment: PlacesAndWindsFragment)
}

@Module
abstract class PlacesAndWindsModule {

    @PlacesAndWindsScope
    @Binds
    abstract fun bindInteractor(interactor: PlacesAndWindsInteractorImpl): PlacesAndWindsInteractor

    @PlacesAndWindsScope
    @Binds
    @IntoMap
    @ViewModelKey(PlacesAndWindsViewModel::class)
    abstract fun bindViewModel(viewModel: PlacesAndWindsViewModel): ViewModel
}