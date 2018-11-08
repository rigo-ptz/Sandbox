package com.jollypanda.sandbox.data.repository.paging

import androidx.paging.PagedList
import androidx.annotation.MainThread
import com.jollypanda.sandbox.data.repository.DbNetworkRedditPostsRepo
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost
import com.jollypanda.sandbox.data.sources.network.RedditApi
import com.jollypanda.sandbox.data.sources.network.entities.ListingResponse
import com.jollypanda.sandbox.data.sources.network.paging.NetworkState
import com.jollypanda.sandbox.utils.liveDataOf
import java.io.IOException


/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
class RedditPostsBoundaryCallback(
    private val api: RedditApi,
    private val handleResponse: (ListingResponse) -> Unit,
    private val pageSize: Int = DbNetworkRedditPostsRepo.DEFAULT_PAGE_SIZE
) : PagedList.BoundaryCallback<RedditPost>() {
    
    val networkState = liveDataOf(NetworkState.LOADED)
    
    @MainThread
    override fun onZeroItemsLoaded() {
        networkState.postValue(NetworkState.LOADING)
        api.getTop("androiddev", pageSize)
        val disp = api
            .getTop("androiddev", pageSize)
            .subscribe({
                           if (it.isSuccessful) {
                               networkState.postValue(NetworkState.LOADED)
                               handleResponse(it.body()!!)
                           } else {
                               // Check code, raise error
                           }
                       },
                       {
                           val msg = if (it is IOException) "No network" else it.message ?: "Unknown error"
                           networkState.postValue(NetworkState.ERROR(msg))
                       }
            )
        
    }
    
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
        val disp = api.getTopAfter("androiddev", itemAtEnd.name, pageSize)
            .subscribe({
                           if (it.isSuccessful) {
                               networkState.postValue(NetworkState.LOADED)
                               handleResponse(it.body()!!)
                           } else {
                               // Check code, raise error
                           }
                       },
                       {
                           val msg = if (it is IOException) "No network" else it.message ?: "Unknown error"
                           networkState.postValue(NetworkState.ERROR(msg))
                       }
            )
    }
    
    @MainThread
    override fun onItemAtFrontLoaded(itemAtFront: RedditPost) {
        // Just a test
    }
}