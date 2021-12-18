package com.jollypanda.sandbox.core.di.modules

import com.jollypanda.sandbox.data.repository.DbNetworkRedditPostsRepo
import com.jollypanda.sandbox.data.sources.database.RedditDb
import com.jollypanda.sandbox.data.sources.network.RedditApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideRedditRepo(db: RedditDb, api: RedditApi) = DbNetworkRedditPostsRepo(db, api)
    
}
