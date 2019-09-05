package com.omarkrostom.mindownloader.di.binders

import com.omarkrostom.mindownloader.ui.AppContainerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModuleBinder {
    @ContributesAndroidInjector
    abstract fun appContainerActivity(): AppContainerActivity
}