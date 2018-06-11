package com.msalcedo.dinnews.screen.home.mvvm

import android.arch.lifecycle.ViewModel
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.Filter

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class HomeViewModel : ViewModel(), HomeContract.ViewModel {

    private val TAG = "TAG_${HomeViewModel::class.java.simpleName}"
    private var filter: Filter = Filter()

    fun init(api: InterfaceApi) {
    }

    fun start() {

    }

    fun getFilter(): Filter {
        return filter
    }

    fun setFilter(filter: Filter) {
        this.filter = filter
    }

    fun landscapeMode(): Boolean {
        return Application.component.resources().getBoolean(R.bool.twoPaneMode)
    }

}