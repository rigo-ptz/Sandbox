package com.jollypanda.sandbox.data.repository

import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.jollypanda.sandbox.data.repository.paging.RedditPostsBoundaryCallback
import com.jollypanda.sandbox.data.repository.paging.RepoResultListing
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost
import com.jollypanda.sandbox.data.sources.database.RedditDb
import com.jollypanda.sandbox.data.sources.network.RedditApi
import com.jollypanda.sandbox.data.sources.network.entities.ListingResponse

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
class DbNetworkRedditPostsRepo(
    private val db: RedditDb,
    private val api: RedditApi,
    private val pageSize: Int = DEFAULT_PAGE_SIZE
) : RedditPostsRepository {
    
    override fun getPosts(pageSize: Int): RepoResultListing<RedditPost> {
        val boundaryCallback = RedditPostsBoundaryCallback(api,
                                                           this::savePostsToDb,
                                                           pageSize)
        
        val dataSourceFactory = db.posts().getAllPosts()
    
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setPrefetchDistance(0)
            .setEnablePlaceholders(true)
            .build()
        
        val builder = LivePagedListBuilder(dataSourceFactory, config).setBoundaryCallback(boundaryCallback)
        
        return RepoResultListing(builder.build(), boundaryCallback.networkState)
    }
    
    private fun savePostsToDb(posts: ListingResponse) {
        posts.data.children.let { posts ->
            db.runInTransaction {
                val start = db.posts().getNextIndexInSubreddit("androiddev")
                val items = posts.mapIndexed { index, child ->
                    child.data.indexInResponse = start + index
                    child.data
                }
                db.posts().insert(items)
            }
        }
    }
    
    companion object {
        val DEFAULT_PAGE_SIZE = 10
    }
    
}