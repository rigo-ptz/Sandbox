package com.jollypanda.sandbox.data.sources.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.jollypanda.sandbox.data.sources.common.entities.RedditPost

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
@Database(entities = [(RedditPost::class)], version = 1, exportSchema = false)
abstract class RedditDb : RoomDatabase() {
    
    abstract fun posts(): RedditPostDao
    
    companion object {
        fun create(context: Context, isInMemoryDb : Boolean): RedditDb {
            val databaseBuilder =
                    if (isInMemoryDb)
                        Room.inMemoryDatabaseBuilder(context, RedditDb::class.java)
                    else
                        Room.databaseBuilder(context, RedditDb::class.java, "reddit.db")
            
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    
}