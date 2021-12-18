package com.jollypanda.sandbox.data.repository.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jollypanda.sandbox.data.sources.network.paging.NetworkState

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
data class RepoResultListing<T : Any>(
    val data: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>
)
