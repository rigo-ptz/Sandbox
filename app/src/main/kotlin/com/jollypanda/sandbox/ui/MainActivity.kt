package com.jollypanda.sandbox.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jollypanda.sandbox.R
import com.jollypanda.sandbox.ui.chats.ChatActivity
import com.jollypanda.sandbox.ui.reddit_paging.PagingActivity
import com.jollypanda.sandbox.utils.Checker
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Yamushev Igor
 * @since  02.02.18
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnChat.setOnClickListener { startActivity(ChatActivity.getStartIntent(this)) }
        btnPaging.setOnClickListener { startActivity(PagingActivity.getStartIntent(this)) }
        
//        makediffChecks()
    }
    
    private fun makediffChecks() {
        Checker.checkPlurals(resources)
        Checker.checkRx()
    }
    
}