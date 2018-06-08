package com.jollypanda.sandbox.data.repository

import com.jollypanda.sandbox.data.repository.paging.RepoResultListing
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
interface RedditPostsRepository {
    fun getPosts(pageSize: Int): RepoResultListing<RedditPost>
}