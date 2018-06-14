package com.msalcedo.dinnews.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class RxLifeObserver : LifecycleObserver {

    var disposables: CompositeDisposable? = null
        private set
    var disposablesForever: CompositeDisposable? = null
        private set

    private val isCompositeDisposableEmpty: Boolean
        get() = disposables == null

    private val isCompositeDisposableForeverEmpty: Boolean
        get() = disposablesForever == null

    fun addDisposable(disposable: Disposable) {
        if (isCompositeDisposableEmpty) {
            this.disposables = CompositeDisposable()
        }
        this.disposables!!.add(disposable)
    }

    fun addDisposableForever(disposable: Disposable) {
        if (isCompositeDisposableForeverEmpty) {
            disposablesForever = CompositeDisposable()
        }
        this.disposablesForever!!.add(disposable)
    }

    fun clearDisposables() {
        if (!isCompositeDisposableEmpty) {
            this.disposables!!.clear()
        }
    }

    fun clearDisposablesForever() {
        if (!isCompositeDisposableForeverEmpty) {
            this.disposablesForever!!.clear()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        clearDisposables()
    }

}