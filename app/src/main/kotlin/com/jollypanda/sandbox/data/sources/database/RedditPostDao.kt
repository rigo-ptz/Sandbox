package com.jollypanda.sandbox.data.sources.database

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
@Dao
interface RedditPostDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insert(posts: List<RedditPost>)
    
    @Query("SELECT * FROM posts")
    fun getAllPosts(): DataSource.Factory<Int, RedditPost>
    
    @Query("SELECT MAX(indexInResponse) + 1 FROM posts WHERE subreddit = :subreddit")
    fun getNextIndexInSubreddit(subreddit: String) : Int
    
    @Query("DELETE FROM posts")
    fun deleteAllPosts()
}