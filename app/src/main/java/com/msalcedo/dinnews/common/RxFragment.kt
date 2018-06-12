package com.msalcedo.dinnews.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
open class RxFragment : Fragment() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        init()

        if (resources.getBoolean(R.bool.twoPaneMode)) {
            initLandscape()
        } else {
            initPortrait()
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun initPortrait() {}

    open fun initLandscape() {}

    open fun init() {}

    fun addDisposable(disposable: Disposable) {
        rxLifeObserver.addDisposable(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        rxLifeObserver.addDisposableForever(disposable)
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
