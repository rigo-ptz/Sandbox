package com.jollypanda.sandbox.data.sources.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost
import com.jollypanda.sandbox.data.sources.network.RedditApi
import java.io.IOException

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
class RedditPostsSource(val api: RedditApi) : ItemKeyedDataSource<String, RedditPost>() {
    
    val networkState = MutableLiveData<NetworkState>()
    
    override fun getKey(item: RedditPost): String = item.name
    
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<RedditPost>) {
        networkState.postValue(NetworkState.LOADING)
        val disp = api
            .getTop("androiddev", params.requestedLoadSize)
            .subscribe({
                           if (it.isSuccessful) {
                               val items = it.body()?.data?.children?.map { it.data } ?: emptyList()
                               networkState.postValue(NetworkState.LOADED)
                               callback.onResult(items)
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
    
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        networkState.postValue(NetworkState.LOADING)
        val disp = api.getTopAfter("androiddev", params.key, params.requestedLoadSize)
            .subscribe({
                           if (it.isSuccessful) {
                               val items = it.body()?.data?.children?.map { it.data } ?: emptyList()
                               networkState.postValue(NetworkState.LOADED)
                               callback.onResult(items)
                           } else {
                               // Check code, raise error
                           }
                       },
                       {
                           val msg = if (it is IOException) "No network" else it.message ?: "Unknown error"
                           networkState.postValue(NetworkState.ERROR(msg))
                       })
    }
    
    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        // We make append only
    }
    
}