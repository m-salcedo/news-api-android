package com.msalcedo.dinnews.screen.splash.mvvm

import android.arch.lifecycle.ViewModel
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class SplashViewModel : ViewModel(), SplashContract.ViewModel {

    private val TAG = "TAG_${SplashViewModel::class.java.simpleName}"

   fun init(api: InterfaceApi) {
   }

    fun start(): Observable<Boolean> {
        return Observable.just(true).delay(2000, TimeUnit.MILLISECONDS)
    }

}