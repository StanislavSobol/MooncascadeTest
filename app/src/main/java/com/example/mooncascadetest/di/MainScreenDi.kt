package com.example.mooncascadetest.di

import androidx.lifecycle.ViewModel
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractor
import com.example.mooncascadetest.domain.mainscreen.MainScreenInteractorImpl
import com.example.mooncascadetest.presentation.mainscreen.MainScreenFragment
import com.example.mooncascadetest.presentation.mainscreen.MainScreenViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScreenScope

@MainScreenScope
@Component(
    modules = [(MainScreenModule::class)],
    dependencies = [(AppComponent::class)]
)
interface MainScreenComponent {

    fun inject(fragment: MainScreenFragment)
}

@Module
abstract class MainScreenModule {

    @MainScreenScope
    @Binds
    abstract fun bindInteractor(interactor: MainScreenInteractorImpl): MainScreenInteractor

    @MainScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindViewModel(viewModel: MainScreenViewModel): ViewModel
}