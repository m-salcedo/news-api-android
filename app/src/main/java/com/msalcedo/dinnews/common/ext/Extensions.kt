package com.msalcedo.dinnews.common.ext

import android.content.SharedPreferences
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
inline fun <reified T: Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }

fun SharedPreferences.saveString(key: String, value: String) = edit().putString(key, value).apply()

fun ViewGroup.inflate(resourceId: Int, autoAttach: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resourceId, this, autoAttach)
}

fun EditText.setError(resId: Int): Unit {
    error = context.resources.getString(resId)
}