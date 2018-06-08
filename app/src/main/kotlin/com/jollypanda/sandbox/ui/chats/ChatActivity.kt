package com.jollypanda.sandbox.ui.chats

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewTreeObserver
import android.view.animation.OvershootInterpolator
import com.jollypanda.sandbox.R
import kotlinx.android.synthetic.main.activity_chat.*


/**
 * @author Yamushev Igor
 * @since  01.02.18
 */
class ChatActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }

    enum class ContainersState {
        OPENED,
        CLOSED
    }

    enum class MotionState {
        CALM,
        MOVE
    }

    private var state: ContainersState =
            ContainersState.OPENED
    private var motionState: MotionState =
            MotionState.CALM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        initGuests()
        initFirstState(savedInstanceState)
    }

    private fun initGuests() {
        val detector = GestureDetectorCompat(this, this)
        rootView.setOnTouchListener { view, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_UP
                && motionState == MotionState.MOVE
                && motionEvent.rawX < rootView.width / 2) {
                openContainers()
                return@setOnTouchListener true
            } else if (motionEvent.actionMasked == MotionEvent.ACTION_UP
                       && motionState == MotionState.MOVE
                       && motionEvent.rawX >= rootView.width / 2) {
                closeContainers()
                return@setOnTouchListener true
            } else {
                detector.onTouchEvent(motionEvent)
            }
        }
    }

    private fun initFirstState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            fl1.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    fl1.x = (rootView.width - fl1.width).toFloat()
                    fl2.x = fl1.x + fl1.width
                    state = ContainersState.CLOSED
                    fl1.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    override fun onBackPressed() {
        if (state == ContainersState.CLOSED) {
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        } else
            closeContainers()
    }

    private fun openContainers() {
        val openFl1Animation = ObjectAnimator.ofFloat(fl1, "x", fl1.x, 0f)
                .setDuration(250)
        openFl1Animation.interpolator = OvershootInterpolator()
        openFl1Animation.addUpdateListener {
            val x = (it.animatedValue as Float)
            fl2.x = x + fl1.width
        }
        openFl1Animation.start()
        state = ContainersState.OPENED
        motionState = MotionState.CALM
    }

    private fun closeContainers() {
        val openFl1Animation = ObjectAnimator.ofFloat(fl1, "x", fl1.x, (rootView.width - fl1.width).toFloat())
                .setDuration(250)
        openFl1Animation.interpolator = OvershootInterpolator()
        openFl1Animation.addUpdateListener {
            val x = (it.animatedValue as Float)
            fl2.x = x + fl1.width
        }
        openFl1Animation.start()
        state = ContainersState.CLOSED
        motionState = MotionState.CALM
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, dx: Float, dy: Float): Boolean {
        val dxx = e1.rawX - e2.rawX
        Log.e("SCROLL", "scroll on X = $dxx")
        Log.e("SCROLL EVENT", "${e2.actionMasked}")
        if (fl1.x >= 0 && (fl1.x + fl1.width <= rootView.width)) {
            fl1.x -= dx
            fl2.x -= dx

            if (fl1.x < 0) {
                fl1.x = 0f
                fl2.x = fl1.width.toFloat()
                state = ContainersState.OPENED
            }

            if (fl1.x + fl1.width > rootView.width) {
                fl1.x = (rootView.width - fl1.width).toFloat()
                fl2.x = fl1.x + fl1.width
                state = ContainersState.CLOSED
            }
        }
        motionState = MotionState.MOVE
        return true
    }

    override fun onShowPress(p0: MotionEvent?) { }

    override fun onSingleTapUp(ev: MotionEvent): Boolean {
        if (state == ContainersState.CLOSED &&
            ev.rawX > 0 && ev.rawX <= fl1.x) {
            onBackPressed()
        }
        motionState = MotionState.CALM
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean { return true }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, p2: Float, p3: Float): Boolean { return true }

    override fun onLongPress(p0: MotionEvent?) { }

}