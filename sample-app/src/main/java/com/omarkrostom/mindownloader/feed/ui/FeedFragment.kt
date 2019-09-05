package com.omarkrostom.mindownloader.feed.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omarkrostom.minddownloadlibrary.managers.DownloadManager
import com.omarkrostom.mindownloader.R
import com.omarkrostom.mindownloader.feed.adapters.FeedAdapter
import com.omarkrostom.mindownloader.architecture.BaseNavFragment
import com.omarkrostom.mindownloader.getPrivateViewModel
import com.omarkrostom.mindownloader.models.PostItem
import com.omarkrostom.mindownloader.feed.viewModels.FeedViewModel
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment: BaseNavFragment() {
    /**
     * Properties
     */
    @Inject
    lateinit var minDownloadManager: DownloadManager

    @Inject
    lateinit var feedViewModel: FeedViewModel

    private val feedAdapter by lazy {
        FeedAdapter(this::onPostItemClicked, minDownloadManager)
    }

    /**
     * Public methods
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initLayout()
        listenForObservers()
        pullLatestFeed()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun pullLatestFeed() {
        feedViewModel.pullLatestFeed()
    }

    override fun getLayoutId(): Int = R.layout.fragment_feed

    /**
     * Private methods
     */
    private fun initLayout() {
        rvFeed.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvFeed.adapter = feedAdapter
    }

    private fun listenForObservers() {
        feedViewModel.feedResponse.observe(this, Observer {
            feedAdapter.setFeedItems(it)
        })
    }

    private fun onPostItemClicked(postItem: PostItem) {
        Toast.makeText(activity, "Posted by Author: ${postItem.user.username}", Toast.LENGTH_LONG).show()
    }
}