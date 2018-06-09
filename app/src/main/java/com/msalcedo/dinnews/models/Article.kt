package com.msalcedo.dinnews.models

import com.google.gson.Gson
import com.squareup.moshi.Json


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Article {

    @Json(name = "source")
    val source: Source? = null
    @Json(name = "author")
    val author: Any? = null
    @Json(name = "title")
    val title: String? = null
    @Json(name = "description")
    val description: String? = null
    @Json(name = "url")
    val url: String? = null
    @Json(name = "urlToImage")
    val urlToImage: Any? = null
    @Json(name = "publishedAt")
    val publishedAt: String? = null

    override fun toString(): String {
        return Gson().toJson(this)
    }
}