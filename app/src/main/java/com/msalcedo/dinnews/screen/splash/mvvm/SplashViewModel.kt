package com.msalcedo.dinnews.screen.splash.mvvm

import android.arch.lifecycle.ViewModel
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.ResponseSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class SplashViewModel : ViewModel(), SplashContract.ViewModel {

    private val TAG = "TAG_${SplashViewModel::class.java.simpleName}"

    private lateinit var sources: Single<ResponseSource>

    fun init(api: InterfaceApi) {
       sources = api.sources()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
   }

    fun start(): Single<ResponseSource> {
        return sources
    }

}