package com.jollypanda.sandbox.data.sources.network.paging

import android.annotation.SuppressLint
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jollypanda.sandbox.R
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost
import kotlinx.android.synthetic.main.item_reddit_post.view.*

/**
 * @author Yamushev Igor
 * @since  08.06.18
 */
class RedditPostsAdapter : PagedListAdapter<RedditPost, RecyclerView.ViewHolder>(DIFF_UTIL) {
    
    enum class ViewTypes {
        LOAD_VIEW,
        ITEM_VIEW
    }
    
    var networkState: NetworkState? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                ViewTypes.LOAD_VIEW.ordinal -> RedditLoadingViewHolder.create(parent)
                ViewTypes.ITEM_VIEW.ordinal -> RedditPostViewHolder.create(parent)
                else -> throw IllegalArgumentException("unknown view type $viewType")
            }
    
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (getItemViewType(position)) {
                ViewTypes.LOAD_VIEW.ordinal -> (holder as RedditLoadingViewHolder).bind()
                ViewTypes.ITEM_VIEW.ordinal -> (holder as RedditPostViewHolder).bind(getItem(position))
                else -> {}
            }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        // Update data in holder
//        if (payloads.isNotEmpty()) {
//            val item = getItem(position)
//            (holder as RedditPostViewHolder).update(item)
//        } else {
//            onBindViewHolder(holder, position)
//        }
        onBindViewHolder(holder, position)
    }
    
    override fun getItemViewType(position: Int): Int =
            if (networkState?.status != Satus.SUCCESS)
                ViewTypes.LOAD_VIEW.ordinal
            else
                ViewTypes.ITEM_VIEW.ordinal
    
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                    oldItem.name == newItem.name
    
            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean =
                    oldItem == newItem
        }
    }
}

class RedditPostViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    
    @SuppressLint("CheckResult")
    fun bind(post: RedditPost?) {
        itemView.tvTitle.text = post?.title
        itemView.tvSubtitle.text = post?.author
        itemView.tvScore.text = post?.score?.toString() ?: 0.toString()
        
        Glide.with(itemView.ivPostImage).load(post?.imageUrl).into(itemView.ivPostImage)
    }
    
    companion object {
        fun create(parentView: ViewGroup): RedditPostViewHolder {
            val view = LayoutInflater.from(parentView.context).inflate(R.layout.item_reddit_post, parentView, false)
            return RedditPostViewHolder(view)
        }
    }
}

class RedditLoadingViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
    
    fun bind() { }
    companion object {
        fun create(parentView: ViewGroup): RedditLoadingViewHolder {
            val view = LayoutInflater.from(parentView.context).inflate(R.layout.item_reddit_post_loading, parentView, false)
            return RedditLoadingViewHolder(view)
        }
    }
}

