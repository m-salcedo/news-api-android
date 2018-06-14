package com.msalcedo.dinnews.screen.splash.mvvm.view

import android.animation.Animator
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import java.lang.ref.WeakReference

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
abstract class BaseIndicatorController {

    private val TAG = "TAG_${BaseIndicatorController::class.java.simpleName}"

    private var mTarget: WeakReference<View>? = null

    private var mAnimators: List<Animator>? = null


    fun setTarget(target: View) {
        this.mTarget = WeakReference(target)
    }

    fun getTarget(): View? {
        return if (mTarget != null) mTarget!!.get() else null
    }


    fun getWidth(): Int {
        return if (getTarget() != null) getTarget()!!.width else 0
    }

    fun getHeight(): Int {
        return if (getTarget() != null) getTarget()!!.height else 0
    }

    fun postInvalidate() {
        if (getTarget() != null) {
            getTarget()!!.postInvalidate()
        }
    }

    /**
     * draw indicator
     * @param canvas
     * @param paintList
     */
    abstract fun draw(canvas: Canvas, paintList: List<Paint>)

    /**
     * create animation or animations
     */
    abstract fun createAnimation(): List<Animator>

    fun initAnimation() {
        mAnimators = createAnimation()
    }

    /**
     * make animation to start or end when target
     * view was be Visible or Gone or Invisible.
     * make animation to cancel when target view
     * be onDetachedFromWindow.
     * @param animStatus
     */
    fun setAnimationStatus(animStatus: AnimStatus) {
        if (mAnimators == null) {
            return
        }
        val count = mAnimators!!.size
        for (i in 0 until count) {
            val animator = mAnimators!![i]
            val isRunning = animator.isRunning()
            when (animStatus) {
                BaseIndicatorController.AnimStatus.START -> if (!isRunning) {
                    animator.start()
                }
                BaseIndicatorController.AnimStatus.END -> if (isRunning) {
                    animator.end()
                }
                BaseIndicatorController.AnimStatus.CANCEL -> if (isRunning) {
                    animator.cancel()
                }
            }
        }
    }


    enum class AnimStatus {
        START, END, CANCEL
    }

}