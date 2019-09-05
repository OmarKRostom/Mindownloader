package com.omarkrostom.mindownloader.di.components

import com.omarkrostom.mindownloader.MindownloaderSampleApp
import com.omarkrostom.mindownloader.di.modules.AppModule
import com.omarkrostom.mindownloader.feed.di.FeedModule
import com.omarkrostom.mindownloader.feed.di.FeedViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.BindsInstance


@Singleton
@Component(
    modules = [
        AppModule::class,
        FeedModule::class,
        FeedViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {
    fun inject(application: MindownloaderSampleApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MindownloaderSampleApp): Builder

        fun build(): AppComponent
    }
}