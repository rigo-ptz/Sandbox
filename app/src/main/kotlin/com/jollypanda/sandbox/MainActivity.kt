package com.jollypanda.sandbox

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jollypanda.sandbox.chat_containers.ChatActivity
import io.reactivex.Observable
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
        checkRx()
    }
    
    private fun checkRx() {
        val array = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Observable.just(array)
            .flatMapIterable { it }
            .buffer(20)
            .subscribe {
                Log.e("CHUNK", it.toString())
            }
    }
}