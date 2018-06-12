package com.msalcedo.dinnews.screen.filter.mvvm

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.models.Source
import com.msalcedo.dinnews.models.empty
import com.msalcedo.dinnews.screen.filterlist.FilterListActivity
import com.msalcedo.dinnews.screen.splash.SplashActivity
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

class FilterViewModel : ViewModel(), FilterContract.ViewModel {

    private val TAG = "TAG_${FilterViewModel::class.java.simpleName}"
    private var filter: Filter = Filter()
    var starting: Boolean = false

    fun setBundle(arguments: Bundle) {
        filter = Filter.adapter.fromJson(arguments.getString(Filter.KEY, "{}"))
        starting = arguments.getBoolean(SplashActivity.KEY, false)
    }

    fun getSortBy(): String {
        return if (filter.sortBy.empty()) Application.component.resources().getString(R.string.sort_default_name) else filter.sortBy!!.name!!
    }

    fun getCountry(): String {
        return if (filter.country.empty()) "" else filter.country!!.name!!
    }

    fun getLanguage(): String {
        return if (filter.language.empty()) "" else filter.language.name!!
    }

    fun categoryAndCountryVisibility(): Int {
        return if (filter.sources.empty()) return View.VISIBLE else View.GONE
    }

    fun getCategory(): String {
        return if (filter.category.empty()) "" else filter.category!!.name!!
    }

    fun getSource(): String {
        return if (filter.sources.empty()) "" else filter.sources.name!!
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

    fun setResult(requestCode: Int, data: Intent) {
        val source = Source.adapter.fromJson(data.getStringExtra(Source.KEY))

        when (requestCode) {
            FilterListActivity.FILTER_CATEGORIES -> {
                filter.category = source
            }
            FilterListActivity.FILTER_COUNTRIES -> {
                filter.country = source
            }
            FilterListActivity.FILTER_LANGUAGES -> {
                filter.language = source
            }
            FilterListActivity.FILTER_SORT_BY -> {
                filter.sortBy = source
            }
            FilterListActivity.FILTER_SOURCES -> {
                filter.sources = source
            }
        }
    }

}