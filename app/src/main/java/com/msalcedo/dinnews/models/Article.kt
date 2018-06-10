package com.msalcedo.dinnews.models

import com.google.gson.Gson
import com.msalcedo.dinnews.common.ext.empty
import com.squareup.moshi.Json


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Article {

    @Json(name = "source")
    val source: Source? = null
    @Json(name = "author")
    val author: String? = null
    @Json(name = "title")
    val title: String? = null
    @Json(name = "description")
    val description: String? = null
    @Json(name = "url")
    val url: String? = null
    @Json(name = "urlToImage")
    val urlToImage: String? = null
    get() {return if (field == null || field.empty()) PLACEHOLDER_IMAGE else field}
    @Json(name = "publishedAt")
    val publishedAt: String? = null

    companion object {
        const val PLACEHOLDER_IMAGE: String = "https://cdn.pixabay.com/photo/2017/03/14/16/57/silver-2143730_1280.jpg"
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}