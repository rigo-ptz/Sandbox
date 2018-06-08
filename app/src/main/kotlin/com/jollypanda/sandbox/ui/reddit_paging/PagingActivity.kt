package com.jollypanda.sandbox.ui.reddit_paging

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jollypanda.sandbox.R
import com.jollypanda.sandbox.data.sources.network.paging.RedditPostsAdapter
import com.jollypanda.sandbox.utils.viewModel
import kotlinx.android.synthetic.main.activity_paging.*

/**
 * @author Yamushev Igor
 * @since  08.06.18
 */
class PagingActivity : AppCompatActivity() {
    
    private val vm by viewModel(RedditViewModel::class)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        initAdapter()
    }
    
    private fun initAdapter() {
        val adapter = RedditPostsAdapter()
        rvPosts.adapter = adapter
        vm.posts.observe(this, Observer {
            adapter.submitList(it)
        })
        vm.networkState.observe(this, Observer {
            adapter.networkState = it
        })
    }
    
    companion object {
        fun getStartIntent(context: Context) = Intent(context, PagingActivity::class.java)
    }
}