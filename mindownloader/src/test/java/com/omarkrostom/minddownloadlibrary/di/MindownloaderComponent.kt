package com.omarkrostom.minddownloadlibrary.di

import com.omarkrostom.minddownloadlibrary.CacheManagerTest
import com.omarkrostom.minddownloadlibrary.DownloadManagerTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MindownloaderModule::class])
interface MindownloaderComponent {
    fun inject(cacheManagerTest: CacheManagerTest)
    fun inject(downloadManagerTest: DownloadManagerTest)
}