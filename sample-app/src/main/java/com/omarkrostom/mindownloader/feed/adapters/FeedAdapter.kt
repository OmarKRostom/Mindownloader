package com.omarkrostom.mindownloader.feed.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.omarkrostom.minddownloadlibrary.fileResources.ImageResource
import com.omarkrostom.minddownloadlibrary.managers.DownloadManager
import com.omarkrostom.mindownloader.R
import com.omarkrostom.mindownloader.bindItemLayout
import com.omarkrostom.mindownloader.models.PostItem
import kotlinx.android.synthetic.main.item_layout_feed.view.*

class FeedAdapter(
    private val onPostItemClicked: (postItem: PostItem) -> Unit,
    private val minDownloadManager: DownloadManager
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    private val feedItems: ArrayList<PostItem> = ArrayList()

    fun setFeedItems(feedItems: ArrayList<PostItem>) {
        this.feedItems.addAll(feedItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
        FeedViewHolder(
            parent.bindItemLayout(R.layout.item_layout_feed),
            onPostItemClicked,
            minDownloadManager
        )

    override fun getItemCount(): Int = feedItems.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(feedItems[position])
    }

    class FeedViewHolder(
        itemView: View,
        private val onPostItemClicked: (postItem: PostItem) -> Unit,
        private val minDownloadManager: DownloadManager
    )
        : RecyclerView.ViewHolder(itemView) {
        fun onBind(postItem: PostItem) {
            val imageResource = ImageResource()
            itemView.setOnClickListener { onPostItemClicked(postItem) }
            itemView.tvLikeCount.text = postItem.likes.toString()
            minDownloadManager.loadResource(
                postItem.postItemUrls.regular,
                imageResource,
                {
                    itemView.ivPostImage.setImageBitmap(it)
                },
                { networkError ->
                    Toast.makeText(itemView.context,
                        "Error loading resource ${networkError.errorValue}",
                        Toast.LENGTH_LONG
                    ).show()
                })
        }
    }
}