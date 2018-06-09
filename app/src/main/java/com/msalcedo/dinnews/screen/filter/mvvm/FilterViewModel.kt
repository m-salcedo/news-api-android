package com.msalcedo.dinnews.screen.filter.mvvm

import android.arch.lifecycle.ViewModel
import com.msalcedo.dinnews.app.modules.api.InterfaceApi

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

class FilterViewModel : ViewModel(), FilterContract.ViewModel {

    private val TAG = "TAG_${FilterViewModel::class.java.simpleName}"

    fun init(api: InterfaceApi) {
    }

    fun start() {

    }

}