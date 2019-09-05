package com.omarkrostom.mindownloader.di.modules

import com.omarkrostom.minddownloadlibrary.managers.CacheManager
import com.omarkrostom.minddownloadlibrary.managers.DownloadManager
import com.omarkrostom.minddownloadlibrary.models.Cache
import com.omarkrostom.mindownloader.Config
import com.omarkrostom.mindownloader.di.binders.AppModuleBinder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppModuleBinder::class])
class AppModule {
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

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesDownloadManager(httpClient: OkHttpClient, cacheManager: CacheManager) =
        DownloadManager(httpClient, cacheManager)

    @Provides
    @Singleton
    fun providesCacheManager() = CacheManager(Cache.Configuration())

}