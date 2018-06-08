package com.jollypanda.sandbox.core.di

import com.jollypanda.sandbox.core.App
import com.jollypanda.sandbox.data.repository.DbNetworkRedditPostsRepo
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
inline fun <T> inject(crossinline block: Injector.() -> T): Lazy<T> = lazy { Injector.getInstance().block() }

class Injector {
    
    @Inject
    lateinit var redditPostsRepository: DbNetworkRedditPostsRepo
    
    init {
        App.appComponent.injectTo(this)
    }
    
    companion object {
        private var instance: Injector? = null
        
        fun getInstance(): Injector =
                if (instance != null)
                    instance!!
                else {
                    instance = Injector()
                    instance!!
                }
    }
}