package com.jollypanda.sandbox.data.sources.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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