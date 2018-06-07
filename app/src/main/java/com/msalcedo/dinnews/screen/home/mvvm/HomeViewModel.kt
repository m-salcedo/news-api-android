package com.msalcedo.dinnews.screen.home.mvvm

import android.arch.lifecycle.ViewModel
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class HomeViewModel : ViewModel(), HomeContract.ViewModel {

    private val TAG = "TAG_${HomeViewModel::class.java.simpleName}"

    fun init(api: InterfaceApi) {
    }

    fun start() {

    }

}