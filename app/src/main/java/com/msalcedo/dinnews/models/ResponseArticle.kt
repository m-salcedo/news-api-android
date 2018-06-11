package com.msalcedo.dinnews.models

import com.google.gson.Gson
import com.squareup.moshi.Json


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ResponseArticle {

    @Json(name = "status")
    val status: String? = null
    @Json(name = "totalResults")
    val totalResults: Long? = null
    @Json(name = "articles")
    val articles: List<Article>? = null

    override fun toString(): String {
        return Gson().toJson(this)
    }
}