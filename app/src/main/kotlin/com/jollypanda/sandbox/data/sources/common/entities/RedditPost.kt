package com.jollypanda.sandbox.data.sources.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
@Entity(tableName = "posts",
        indices = [(Index(value = ["subReddit"], unique = false))])
data class RedditPost(
    @PrimaryKey
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("score") val score: Int,
    @SerializedName("author") val author: String,
    @SerializedName("subreddit") val subReddit: String,
    @SerializedName("num_comments") val commentsCount: Int,
    @SerializedName("created_utc") val created: Long,
    @SerializedName("thumbnail") val imageUrl: String?,
    @SerializedName("url") val postUrl: String?) {
    
    // to be consistent w/ changing backend order, we need to keep a data like this
    var indexInResponse: Int = -1
}