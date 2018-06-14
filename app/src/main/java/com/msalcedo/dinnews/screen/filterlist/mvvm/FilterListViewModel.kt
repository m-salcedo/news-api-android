package com.msalcedo.dinnews.screen.filterlist.mvvm

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.models.Source
import com.msalcedo.dinnews.models.empty
import com.msalcedo.dinnews.models.nameEmpty
import com.msalcedo.dinnews.screen.filterlist.FilterListActivity

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class FilterListViewModel : ViewModel(), FilterListContract.ViewModel {

    private val TAG = "TAG_${FilterListViewModel::class.java.simpleName}"

    private var item: Source? = null

    private var list: MutableList<Source> = mutableListOf()

    private val hintSearch: String = ""

    private val title: String = ""

    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    fun start(extras: Bundle) {
        var type = extras.getInt(FilterListActivity.FILTER_LIST_REQUEST)

        when (type) {
            FilterListActivity.FILTER_CATEGORIES -> {
                list = Source.createListFromStrings(
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.categories).toList(),
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.categories).toList()
                )
            }
            FilterListActivity.FILTER_COUNTRIES -> {
                list = Source.createListFromStrings(
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.countries).toList(),
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.countries_code).toList()
                )
            }
            FilterListActivity.FILTER_LANGUAGES -> {
                list = Source.createListFromStrings(
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.languages).toList(),
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.languages_code).toList()
                )
            }
            FilterListActivity.FILTER_SORT_BY -> {
                list = Source.createListFromStrings(
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.sortBy).toList(),
                        Application.component.resources().getStringArray(com.msalcedo.dinnews.R.array.sortBy_code).toList()
                )
            }
            FilterListActivity.FILTER_SOURCES -> {
                list = Source.get(context!!)
            }
        }
    }

    fun getSelected(): Intent? {
        val bundle = Bundle()
        bundle.putString(Source.KEY, Source.adapter.toJson(item))
        return Intent().putExtras(bundle)
    }

    fun getList(): MutableList<Source> {
        return list
    }

    fun setSelected(source: Source) {
        item = source
    }

    fun result(): Int {
        return if (item.empty() && (item.nameEmpty())) {
            Activity.RESULT_CANCELED
        } else Activity.RESULT_OK
    }

}