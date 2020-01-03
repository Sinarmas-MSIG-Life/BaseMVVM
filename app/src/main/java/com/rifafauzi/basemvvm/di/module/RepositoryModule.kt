package com.rifafauzi.basemvvm.di.module

import com.rifafauzi.basemvvm.api.ApiService
import com.rifafauzi.basemvvm.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(apiService: ApiService) = MoviesRepository(apiService)

}