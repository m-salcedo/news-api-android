package com.msalcedo.dinnews.models

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.ext.empty
import com.squareup.moshi.Json


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Source() {

    constructor(name: String?, id: String?) : this() {
        this.name = name
        this.id = id
    }

    @Json(name = "name")
    var name: String? = ""
    @Json(name = "id")
    var id: String? = ""
    @Json(name = "description")
    val description: String? = null
    @Json(name = "url")
    val url: String? = null

    override fun toString(): String {
        return adapter.toJson(this)
    }

    companion object {
        const val KEY = "source"
        val adapter = Application.component.moshi().adapter(Source::class.java)!!

        fun createListFromStrings(list: List<String>, listCodes: List<String>): MutableList<Source> {
            var array: MutableList<Source> = mutableListOf()
            for ((i, item) in list.withIndex()) {
                array.add(Source(item, listCodes[i]))
            }

            return array
        }

        fun save(context: Context, list: MutableList<Source>) {
            val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
            val prefsEditor = appSharedPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            prefsEditor.putString(KEY, json)
            prefsEditor.apply()
        }

        fun get(context: Context): MutableList<Source> {
            val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
            val gson = Gson()
            val json = appSharedPrefs.getString(KEY, "")
            return gson.fromJson(json, object : TypeToken<MutableList<Source>>() {}.type)
        }
    }
}

fun Source?.empty() = this == null || this.id.empty()
fun Source?.nameEmpty() = this == null || this.name.empty()
