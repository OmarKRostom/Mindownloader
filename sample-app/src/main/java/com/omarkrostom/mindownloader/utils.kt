package com.omarkrostom.mindownloader

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.omarkrostom.mindownloader.architecture.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Get private view model for View
 */
fun <V> Fragment.getPrivateViewModel(clazz: Class<V>) where V: BaseViewModel
        = ViewModelProviders.of(this).get(clazz)

/**
 * Get shared view model across all views (Fragments)
 */
fun <V> Fragment.getSharedViewModel(clazz: Class<V>) where V: BaseViewModel
        = activity?.run { ViewModelProviders.of(this).get(clazz) }

/**
 * Bind recyclerview item default config
 */
fun ViewGroup.bindItemLayout(layoutId: Int) = LayoutInflater.from(this.context)
    .inflate(layoutId, this, false)

/**
 * Convert RxStream into LiveData
 */
fun <R> Observable<R>.toLiveData(): LiveData<R> {
    val compositeDisposable = CompositeDisposable()
    val mutableLiveData = MutableLiveData<R>()

    val disposable = this.subscribeOn(Schedulers.io())
        .subscribe(
            { mutableLiveData.postValue(it) },
            { Log.e("NETWORK ERROR", it.localizedMessage) }
        )

    compositeDisposable.add(disposable)
    return mutableLiveData
}
