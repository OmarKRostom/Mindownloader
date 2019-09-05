package com.omarkrostom.mindownloader.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment

abstract class BaseNavFragment: DaggerFragment(), LifecycleObserver {

    /**
     * Properties
     */
    protected val navigator by lazy {
        view?.let {
            Navigation.findNavController(it)
        }
    }

    /**
     * Public methods
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutId(), container, false)

    /**
     * Abstract methods
     */
    abstract fun getLayoutId(): Int

}