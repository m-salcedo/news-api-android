package com.msalcedo.dinnews.screen.filter.mvvm

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.Filter
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

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

    fun setSortBy(s: String, position: Int) {
        filter.sortBy = s
        filter.positionSortBy = position
    }

    fun setCountry(s: String, position: Int) {
        filter.country = s
        filter.positionCountry = position
    }

    fun setLanguage(s: String, position: Int) {
        filter.language = s
        filter.positionLanguage = position
    }

    fun setCategory(s: String, position: Int) {
        filter.category = s
        filter.positionCategory = position
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

    fun getFromDate(): String? {
        return filter.from
    }

    fun parserDate(): DateTimeFormatter {
        return DateTimeFormat.forPattern("yyyy-MM-dd")
    }

    fun getToDate(): String? {
        return filter.to
    }

}