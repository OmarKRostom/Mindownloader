package com.omarkrostom.mindownloader.feed.datasources

import com.omarkrostom.mindownloader.feed.remote.FeedApi
import com.omarkrostom.mindownloader.models.PostItem
import io.reactivex.Observable
import javax.inject.Inject

class FeedRemoteDataSource @Inject constructor(private val feedApi: FeedApi) {
    /**
     * Public methods
     */
    fun getLatestField(): Observable<ArrayList<PostItem>> = feedApi.getLatestFeedItems()
}