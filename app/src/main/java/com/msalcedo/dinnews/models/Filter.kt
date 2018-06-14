package com.msalcedo.dinnews.models

import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.ext.empty

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Filter {

    var q: String? = null
    var sortBy: Source? = Source(
            Application.component.resources().getString(R.string.sort_default_name),
            Application.component.resources().getString(R.string.sort_default_code))
    var from: String? = null
    var to: String? = null
    var language: Source = Source()
    var sources: Source = Source()

    var country: Source? = Source()
        get() {
            return if (!sources.empty()) Source() else field
        }
    var category: Source? = Source()
        get() {
            return if (!sources.empty()) Source() else field
        }

    private fun empty(): Boolean {
        return q.empty() &&
        sources.empty()
    }

    override fun toString(): String {
        return adapter.toJson(this)
    }

    fun getKeyWord(): String? {
        return if (q.empty() && empty()) KEYWORD else q
    }

    fun getLanguageDefault(): String? {
        return if (language.empty() && empty()) LANGUAGE else language!!.id
    }

    companion object {
        private val KEYWORD = "trump"
        private val LANGUAGE = "en"
        const val KEY = "filter"
        val adapter = Application.component.moshi().adapter(Filter::class.java)!!
    }
}