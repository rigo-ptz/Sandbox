package com.jollypanda.sandbox.ui.reddit_paging

import androidx.lifecycle.ViewModel
import com.jollypanda.sandbox.core.di.inject

/**
 * @author Yamushev Igor
 * @since  08.06.18
 */
class RedditViewModel : ViewModel() {
    
    private val redditPostsRepo by inject { redditPostsRepository }
    private val redditPostsFromRepo = redditPostsRepo.getPosts(30)
    
    val posts = redditPostsFromRepo.data
    val networkState = redditPostsFromRepo.networkState
    
}