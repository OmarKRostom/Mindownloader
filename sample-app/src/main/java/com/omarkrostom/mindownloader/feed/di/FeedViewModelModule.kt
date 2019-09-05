package com.omarkrostom.mindownloader.feed.di

import androidx.lifecycle.ViewModel
import com.omarkrostom.mindownloader.di.modules.ViewModelKey
import com.omarkrostom.mindownloader.feed.viewModels.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FeedViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel

}