package com.msalcedo.dinnews.models

import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.ext.empty

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Filter {

    companion object {
        private val KEYWORD = "Europ"
        const val KEY = "filter"
        val adapter = Application.component.moshi().adapter(Filter::class.java)!!
    }

    var country: String? = null
        get() {
            return if (sources != null) null else field
        }
    var category: String? = null
        get() {
            return if (sources != null) null else field
        }

    var q: String? = null
    var sortBy: String? = null
    var from: String? = null
    var to: String? = null
    var language: String? = null
    var sources: String? = null

    private fun empty(): Boolean {
        return category.empty() &&
                language.empty() &&
                country.empty()
    }

    override fun toString(): String {
        return adapter.toJson(this)
    }

    fun getKeyWord(): String? {
        return if (q.empty() && empty()) KEYWORD else q
    }
}