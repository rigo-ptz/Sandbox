package com.jollypanda.sandbox.data.sources.network.entities

import com.jollypanda.sandbox.data.sources.common.entities.RedditPost

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
data class ListingResponse(
    val data: ListingData
)

data class ListingData(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?
)

data class RedditChildrenResponse(
    val data: RedditPost
)