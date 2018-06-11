package com.msalcedo.dinnews.models

import android.os.Build
import android.text.Html
import android.text.Spanned
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
    private val title: String? = null
    @Json(name = "description")
    private val description: String? = null
    @Json(name = "url")
    val url: String? = null
    @Json(name = "urlToImage")
    val urlToImage: String? = null
        get() {
            return if (field == null || field.empty()) PLACEHOLDER_IMAGE else field
        }
    @Json(name = "publishedAt")
    val publishedAt: String? = null

    fun getTitle(): Spanned? {
        return when {
            title.empty() -> null
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT)
            else -> Html.fromHtml(title)
        }
    }

    fun getDescription(): Spanned? {
        return when {
            description.empty() -> null
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
            else -> Html.fromHtml(description)
        }
    }

    companion object {
        const val PLACEHOLDER_IMAGE: String = "https://cdn.pixabay.com/photo/2017/03/14/16/57/silver-2143730_1280.jpg"
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}