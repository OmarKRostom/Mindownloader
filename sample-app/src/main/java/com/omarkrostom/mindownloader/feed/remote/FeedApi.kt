package com.omarkrostom.mindownloader.feed.remote

import com.omarkrostom.mindownloader.models.PostItem
import io.reactivex.Observable
import retrofit2.http.GET

interface FeedApi {

    @GET("raw/wgkJgazE")
    fun getLatestFeedItems(): Observable<ArrayList<PostItem>>

}