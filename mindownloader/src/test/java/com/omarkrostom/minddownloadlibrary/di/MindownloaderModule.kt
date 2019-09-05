package com.omarkrostom.minddownloadlibrary.di

import com.omarkrostom.minddownloadlibrary.managers.CacheManager
import com.omarkrostom.minddownloadlibrary.models.Cache
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class MindownloaderModule {

    @Provides
    @Singleton
    fun providesCacheManager(cacheConfig: Cache.Configuration): CacheManager
            = CacheManager(cacheConfig)

    @Provides
    fun providesCacheConfig(): Cache.Configuration = Cache.Configuration()

    @Provides
    @Singleton
    fun providesOkHttp(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesLogginInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

}