package com.example.mooncascadetest.di

import androidx.lifecycle.ViewModel
import com.example.mooncascadetest.domain.placeorwind.PlaceOrWindInteractor
import com.example.mooncascadetest.domain.placeorwind.PlaceOrWindInteractorImpl
import com.example.mooncascadetest.presentation.placeorwind.PlaceOrWindFragment
import com.example.mooncascadetest.presentation.placeorwind.PlaceOrWindViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PlaceOrWindScope

@PlaceOrWindScope
@Component(
    modules = [(PlaceOrWindModule::class)],
    dependencies = [(AppComponent::class)]
)
interface PlaceOrWindComponent {

    fun inject(fragment: PlaceOrWindFragment)

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun typeInt(id: Int): Builder

        @BindsInstance
        fun id(id: Long): Builder

        fun build(): PlaceOrWindComponent
    }
}

@Module
abstract class PlaceOrWindModule {

    @PlaceOrWindScope
    @Binds
    abstract fun bindInteractor(interactor: PlaceOrWindInteractorImpl): PlaceOrWindInteractor

    @PlaceOrWindScope
    @Binds
    @IntoMap
    @ViewModelKey(PlaceOrWindViewModel::class)
    abstract fun bindViewModel(viewModel: PlaceOrWindViewModel): ViewModel
}