package com.omarkrostom.mindownloader

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.omarkrostom.mindownloader.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MindownloaderSampleApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingActivityInjector

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = dispatchingFragmentInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}