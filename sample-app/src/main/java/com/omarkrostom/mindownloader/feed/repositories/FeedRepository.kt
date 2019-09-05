package com.omarkrostom.mindownloader.feed.repositories

import androidx.lifecycle.LiveData
import com.omarkrostom.mindownloader.models.PostItem
import com.omarkrostom.mindownloader.feed.datasources.FeedRemoteDataSource
import com.omarkrostom.mindownloader.toLiveData
import javax.inject.Inject

class FeedRepository @Inject constructor(private val feedRemote: FeedRemoteDataSource) {
    /**
     * Public methods
     */
    fun pullLatestFeedFromSource(): LiveData<ArrayList<PostItem>> = feedRemote.getLatestField().toLiveData()
}