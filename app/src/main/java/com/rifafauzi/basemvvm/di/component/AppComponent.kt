package com.rifafauzi.basemvvm.di.component

import com.rifafauzi.basemvvm.BaseApp
import com.rifafauzi.basemvvm.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@Singleton
@Component(
    modules = [
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    FragmentModule::class,
    ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApp)
}