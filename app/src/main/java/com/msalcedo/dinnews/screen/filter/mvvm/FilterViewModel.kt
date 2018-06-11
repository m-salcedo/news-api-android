package com.msalcedo.dinnews.screen.filter.mvvm

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.Filter

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

class FilterViewModel : ViewModel(), FilterContract.ViewModel {

    private val TAG = "TAG_${FilterViewModel::class.java.simpleName}"
    private var filter: Filter = Filter()

    fun init(api: InterfaceApi) {
    }

    fun start() {

    }

    fun setBundle(arguments: Bundle) {
        filter = Filter.adapter.fromJson(arguments.getString(Filter.KEY, "{}"))

    }

    fun setSortBy(s: String) {
        filter.sortBy = s
    }

    fun setCountry(s: String) {
        filter.country = s
    }

    fun setLanguage(s: String) {
        filter.language = s
    }

    fun setCategory(s: String) {
        filter.category = s
    }

    fun setKeyWord(s: String) {
        filter.q = s
    }

    fun setDateFrom(s: String) {
        filter.from = s
    }

    fun setDateTo(s: String) {
        filter.to = s
    }

    fun getFilter(): Filter {
        return filter
    }

    fun getKeyWord(): String? {
        return filter.q
    }

}