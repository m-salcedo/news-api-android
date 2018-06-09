package com.msalcedo.dinnews.models

import com.msalcedo.dinnews.app.Application

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Filter {

    companion object {
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

}