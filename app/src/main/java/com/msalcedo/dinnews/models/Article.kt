package com.msalcedo.dinnews.models

import android.os.Build
import android.text.Html
import android.text.Spanned
import com.google.gson.Gson
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.ext.empty
import com.squareup.moshi.Json
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Article {

    companion object {
        const val KEY = "article"
        const val PLACEHOLDER_IMAGE: String =
                "https://cdn.pixabay.com/photo/2017/03/14/16/57/silver-2143730_1280.jpg"
        val adapter = Application.component.moshi().adapter(Article::class.java)!!
    }


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
        get() {
            if (field.empty()) return field
            return parserDate().print(dateISO(field!!))
        }

    fun parserDate(): DateTimeFormatter {
        return DateTimeFormat.forPattern("yyyy-MM-dd")
    }

    fun dateISO(s: String): DateTime {
        return DateTime(s)
    }

    fun getTitle(): Spanned? {
        return when {
            title.empty() -> null
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
                Html.fromHtml(title, Html.FROM_HTML_MODE_COMPACT)
            else -> Html.fromHtml(title)
        }
    }

    fun getDescription(): Spanned? {
        return when {
            description.empty() -> null
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
                Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
            else -> Html.fromHtml(description)
        }
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}