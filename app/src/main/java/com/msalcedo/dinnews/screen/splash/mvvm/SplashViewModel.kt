package com.msalcedo.dinnews.screen.splash.mvvm

import android.arch.lifecycle.ViewModel
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.ResponseSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class SplashViewModel : ViewModel(), SplashContract.ViewModel {

    private val TAG = "TAG_${SplashViewModel::class.java.simpleName}"

    private lateinit var sources: Single<ResponseSource>

    fun init(api: InterfaceApi) {
        sources = api.sources()
    }

    fun start(): Single<ResponseSource> {
        return timer().toCompletable()
                .andThen(sources)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun timer() = Single.just(true).delay(2000, TimeUnit.MILLISECONDS)

}