package com.msalcedo.dinnews.screen.splash.mvvm.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.IntDef
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.msalcedo.dinnews.R
import java.util.*

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class BallsLoadingIndicatorView : View {

    private val TAG = "TAG_${BallsLoadingIndicatorView::class.java.simpleName}"


    @IntDef(flag = true, value = 0)
    annotation class Indicator

    //Sizes (with defaults in DP)
    val DEFAULT_SIZE = 45

    //attrs
    internal var mIndicatorId: Int = 0
    internal var mIndicatorColor: Int = 0

    internal lateinit var mPaintList: MutableList<Paint>

    internal lateinit var mIndicatorController: BaseIndicatorController

    private var mHasAnimation: Boolean = false


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        mIndicatorId = 0
        mIndicatorColor = Color.GRAY

        mPaintList = ArrayList()

        var paint = Paint()
        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        mPaintList.add(paint)

        paint = Paint()
        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        mPaintList.add(paint)

        paint = Paint()
        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        mPaintList.add(paint)

        applyIndicator()
    }

    private fun applyIndicator() {
        mIndicatorController = BallPulseIndicator()
        mIndicatorController.setTarget(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec)
        val height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureDimension(defaultSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize)
        } else {
            result = defaultSize
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawIndicator(canvas)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!mHasAnimation) {
            mHasAnimation = true
            applyAnimation()
        }
    }

    override fun setVisibility(v: Int) {
        if (visibility != v) {
            super.setVisibility(v)
            if (v == View.GONE || v == View.INVISIBLE) {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.END)
            } else {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.START)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mHasAnimation) {
            mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.START)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.CANCEL)
    }

    private fun drawIndicator(canvas: Canvas) {
        mIndicatorController.draw(canvas, mPaintList)
    }

    private fun applyAnimation() {
        mIndicatorController.initAnimation()
    }

    private fun dp2px(dpValue: Int): Int {
        return context.resources.displayMetrics.density.toInt() * dpValue
    }
}