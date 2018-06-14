package com.msalcedo.dinnews.screen.splash.mvvm.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class BallPulseIndicator : BaseIndicatorController() {

    private val TAG = "TAG_${BallPulseIndicator::class.java.simpleName}"

    val SCALE = 1.0f

    //scale x ,y
    private val scaleFloats = floatArrayOf(SCALE, SCALE, SCALE)


    override fun draw(canvas: Canvas, paintList: List<Paint>) {
        val circleSpacing = 20f
        val radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2) / 6
        val x = getWidth() / 2 - (radius * 2 + circleSpacing)
        val y = getHeight() / 2

        for (i in 0..2) {
            canvas.save()
            val translateX = x + radius * 2 * i + circleSpacing * i
            canvas.translate(translateX, y.toFloat())
            canvas.scale(scaleFloats[i], scaleFloats[i])
            canvas.drawCircle(0f, 0f, radius, paintList[i])
            canvas.restore()
        }
    }

    override fun createAnimation(): List<Animator> {
        val animators = ArrayList<Animator>()
        val delays = intArrayOf(120, 240, 360)
        for (i in 0..2) {

            val scaleAnim = ValueAnimator.ofFloat(1F, 0.3F, 1F)

            scaleAnim.duration = 1100
            scaleAnim.repeatCount = -1
            scaleAnim.startDelay = delays[i].toLong()

            scaleAnim.addUpdateListener { animation ->
                scaleFloats[i] = animation.animatedValue as Float
                postInvalidate()
            }
            scaleAnim.start()
            animators.add(scaleAnim)
        }
        return animators
    }

}