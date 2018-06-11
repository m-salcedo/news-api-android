package com.msalcedo.dinnews.models

import com.squareup.moshi.Json


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ResponseSource {

    private val TAG = "TAG_${ResponseSource::class.java.simpleName}"

    @Json(name = "status")
    val status: String? = null
    @Json(name = "sources")
    val sources: List<Source>? = null


}