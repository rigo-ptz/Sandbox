package com.jollypanda.sandbox

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jollypanda.sandbox.chat_containers.ChatActivity
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
    }
}