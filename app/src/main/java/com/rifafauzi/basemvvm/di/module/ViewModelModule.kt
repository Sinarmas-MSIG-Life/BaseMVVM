package com.rifafauzi.basemvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifafauzi.basemvvm.di.factory.ViewModelFactory
import com.rifafauzi.basemvvm.ui.movies.MoviesViewModel
import com.rifafauzi.basemvvm.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun providesMoviesViewModel(viewModel: MoviesViewModel) : ViewModel

}