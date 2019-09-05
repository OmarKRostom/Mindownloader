package com.omarkrostom.mindownloader.feed.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.omarkrostom.mindownloader.architecture.BaseViewModel
import com.omarkrostom.mindownloader.models.PostItem
import com.omarkrostom.mindownloader.feed.repositories.FeedRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val feedRepository: FeedRepository): BaseViewModel() {
    /**
     * Properties
     */
    private val _feedResponse: MediatorLiveData<ArrayList<PostItem>> = MediatorLiveData()
    val feedResponse: LiveData<ArrayList<PostItem>> = _feedResponse

    /**
     * Pull latest feed items
     */
    fun pullLatestFeed() {
        _feedResponse.addSource(feedRepository.pullLatestFeedFromSource()) {
            _feedResponse.postValue(it)
        }
    }

}