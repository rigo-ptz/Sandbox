package com.jollypanda.sandbox

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*


/**
 * @author Yamushev Igor
 * @since  01.02.18
 */
class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {


    override fun onShowPress(p0: MotionEvent?) { }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean { return true }

    override fun onDown(p0: MotionEvent?): Boolean { return true }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, dx: Float, dy: Float): Boolean {
        val dxx = e1.rawX - e2.rawX
        Log.e("SCROLL", "scroll on X = $dxx")
        if (fl1.x >= 0 && (fl1.x + fl1.width <= rootView.width)) {
            fl1.x -= dx
            fl2.x -= dx

            if (fl1.x < 0) {
                fl1.x = 0f
                fl2.x = fl1.width.toFloat()
            }

            if (fl1.x + fl1.width > rootView.width) {
                fl1.x = (rootView.width - fl1.width).toFloat()
                fl2.x = rootView.width.toFloat()
            }
        }
        return true
    }

    override fun onLongPress(p0: MotionEvent?) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInit.setOnClickListener {
            fl1.x = (rootView.width - fl1.width).toFloat()
            fl2.x = rootView.width.toFloat()
        }

        val detector = GestureDetectorCompat(this, this)
        rootView.setOnTouchListener { view, motionEvent ->
            detector.onTouchEvent(motionEvent)
        }
    }


}