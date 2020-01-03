package com.rifafauzi.basemvvm.di.module

import android.app.Application
import android.content.Context
import com.rifafauzi.basemvvm.BaseApp
import com.rifafauzi.basemvvm.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: BaseApp) : Context = app

    @Provides
    @Singleton
    fun provideApplications(app: BaseApp) : Application = app

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

}