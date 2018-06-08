package com.jollypanda.sandbox.data.sources.network

import com.jollypanda.sandbox.data.sources.network.entities.ListingResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
interface RedditApi {
    
    @GET("/r/{subreddit}/hot.json")
    fun getTop(@Path("subreddit") subreddit: String,
               @Query("limit") limit: Int): Single<Response<ListingResponse>>
    
    @GET("/r/{subreddit}/hot.json")
    fun getTopAfter(@Path("subreddit") subreddit: String,
                    @Query("after") after: String,
                    @Query("limit") limit: Int): Single<Response<ListingResponse>>
    
    @GET("/r/{subreddit}/hot.json")
    fun getTopBefore(@Path("subreddit") subreddit: String,
                     @Query("before") before: String,
                     @Query("limit") limit: Int): Single<Response<ListingResponse>>
}