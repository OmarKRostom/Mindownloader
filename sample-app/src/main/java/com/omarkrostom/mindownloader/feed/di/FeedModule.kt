package com.omarkrostom.mindownloader.feed.di

import com.omarkrostom.mindownloader.feed.datasources.FeedRemoteDataSource
import com.omarkrostom.mindownloader.feed.remote.FeedApi
import com.omarkrostom.mindownloader.feed.repositories.FeedRepository
import com.omarkrostom.mindownloader.feed.viewModels.FeedViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [FeedModuleBinder::class])
class FeedModule {

    @Provides
    fun providesFeedViewModel(feedRepository: FeedRepository) = FeedViewModel(feedRepository)

    @Provides
    fun providesFeedApi(retrofit: Retrofit) = retrofit.create(FeedApi::class.java)

    @Provides
    fun providesFeedRepository(feedRemoteDataSource: FeedRemoteDataSource) =
        FeedRepository(feedRemoteDataSource)

    @Provides
    fun providesFeedDataSource(feedApi: FeedApi) =
        FeedRemoteDataSource(feedApi)

}