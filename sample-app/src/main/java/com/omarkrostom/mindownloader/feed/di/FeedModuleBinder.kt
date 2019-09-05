package com.omarkrostom.mindownloader.feed.di

import com.omarkrostom.mindownloader.feed.ui.FeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedModuleBinder {
    @ContributesAndroidInjector
    abstract fun feedFragment(): FeedFragment
}