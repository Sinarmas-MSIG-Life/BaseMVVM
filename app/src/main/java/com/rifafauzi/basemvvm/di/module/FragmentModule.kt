package com.rifafauzi.basemvvm.di.module

import com.rifafauzi.basemvvm.ui.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesMoviesFragment(): MoviesFragment

}