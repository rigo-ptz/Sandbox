package com.jollypanda.sandbox.utils

import android.content.res.Resources
import android.util.Log
import com.jollypanda.sandbox.R
import io.reactivex.Observable

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
object Checker {
    fun checkPlurals(resources: Resources) {
        (0..100).forEach {
            val systemPlural = resources.getQuantityString(R.plurals.plurals_1, it, it)
            val point = resources.getQuantityString(R.plurals.points, it, it)
            Log.e("Plurals", "$systemPlural : $point")
        }
    }
    
    fun checkRx() {
        val array = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Observable.just(array)
            .flatMapIterable { it }
            .buffer(20)
            .subscribe {
                Log.e("CHUNK", it.toString())
            }
    }
}