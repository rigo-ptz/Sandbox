package com.jollypanda.sandbox.core.di.modules

import android.content.Context
import com.jollypanda.sandbox.data.sources.database.RedditDb
import com.jollypanda.sandbox.data.sources.network.RedditApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yamushev Igor
 * @since  08.06.18
 */
@Module
class DataModule {
    
    @Singleton
    @Provides
    fun provideDb(context: Context) = RedditDb.create(context, false)
    
    @Singleton
    @Provides
    fun provideApi() = RedditApiFactory.create()
    
}