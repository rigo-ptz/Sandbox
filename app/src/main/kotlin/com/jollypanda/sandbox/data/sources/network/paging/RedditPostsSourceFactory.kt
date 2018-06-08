package com.jollypanda.sandbox.data.sources.network.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost
import com.jollypanda.sandbox.data.sources.network.RedditApi

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
class RedditPostsSourceFactory(private val api: RedditApi) : DataSource.Factory<String, RedditPost>() {
    
    val sourceLive = MutableLiveData<RedditPostsSource>()
    
    override fun create(): DataSource<String, RedditPost> {
        val source = RedditPostsSource(api)
        sourceLive.postValue(source)
        return source
    }
    
}