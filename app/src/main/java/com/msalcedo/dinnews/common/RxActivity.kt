package com.msalcedo.dinnews.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import com.msalcedo.dinnews.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@SuppressLint("Registered")
open class RxActivity : AppCompatActivity() {

    private val rxLifeObserver = RxLifeObserver()

    open fun initializeComponent() {}

    val disposables: CompositeDisposable?
        get() = rxLifeObserver.disposables

    val disposablesForever: CompositeDisposable?
        get() = rxLifeObserver.disposablesForever

    private var onActivityResultSubject: PublishSubject<Result>? = null
    val onActivityResultObservable: Observable<Result> by lazy {
        onActivityResultSubject = PublishSubject.create()
        onActivityResultSubject as Observable<Result>
    }

    private var onCreateSubject: PublishSubject<Any>? = null
    val onCreateObservable: Observable<Any> by lazy {
        onCreateSubject = PublishSubject.create()
        onCreateSubject as Observable<Any>
    }

    private var onRestartSubject: PublishSubject<Any>? = null
    val onRestartObservable: Observable<Any> by lazy {
        onRestartSubject = PublishSubject.create()
        onRestartSubject as Observable<Any>
    }

    private var onStartSubject: PublishSubject<Any>? = null
    val onStartObservable: Observable<Any> by lazy {
        onStartSubject = PublishSubject.create()
        onStartSubject as Observable<Any>
    }

    private var onResumeSubject: PublishSubject<Any>? = null
    val onResumeObservable: Observable<Any> by lazy {
        onResumeSubject = PublishSubject.create()
        onResumeSubject as Observable<Any>
    }

    private var onPauseSubject: PublishSubject<Any>? = null
    val onPauseObservable: Observable<Any> by lazy {
        onPauseSubject = PublishSubject.create()
        onPauseSubject as Observable<Any>
    }

    private var onStopSubject: PublishSubject<Any>? = null
    val onStopObservable: Observable<Any> by lazy {
        onStopSubject = PublishSubject.create()
        onStopSubject as Observable<Any>
    }

    private var onDestroySubject: PublishSubject<Any>? = null
    val onDestroyObservable: Observable<Any> by lazy {
        onDestroySubject = PublishSubject.create()
        onDestroySubject as Observable<Any>
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeComponent()
        savedInstanceState?.let { onCreateSubject?.onNext(it) }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        init()

        if (resources.getBoolean(R.bool.twoPaneMode)) {
            initLandscape()
        } else {
            initPortrait()
        }
    }

    open fun initPortrait() {}

    open fun initLandscape() {}

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (requestedOrientation != newConfig.orientation) {
            requestedOrientation = if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }

    open fun init() {}

    fun addDisposable(disposable: Disposable) {
        rxLifeObserver.addDisposable(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        rxLifeObserver.addDisposableForever(disposable)
    }

    override fun onRestart() {
        super.onRestart()
        onRestartSubject?.onNext(Any())
    }

    override fun onStart() {
        super.onStart()
        onStartSubject?.onNext(Any())
    }

    override fun onResume() {
        super.onResume()
        onResumeSubject?.onNext(Any())
    }

    override fun onPause() {
        super.onPause()
        onPauseSubject?.onNext(Any())

    }

    override fun onStop() {
        super.onStop()
        onStopSubject?.onNext(Any())
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroySubject?.onNext(Any())
    }

    /**
     * @description Represent the result of 'onActivityResult' params
     */
    data class Result(val requestCode: Int, val resultCode: Int, val data: Intent?)
}
