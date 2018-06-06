package com.msalcedo.dinnews.common.storage

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.msalcedo.dinnews.app.di.AppQualifier
import com.msalcedo.dinnews.app.di.AppScope
import com.msalcedo.dinnews.common.ext.saveString
import com.msalcedo.dinnews.models.Session
import com.msalcedo.dinnews.utils.Constant
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@AppScope
class SessionManager @Inject constructor(@AppQualifier context: Context, moshi: Moshi) {

    private val TAG = "TAG_${SessionManager::class.java.simpleName}"

    private val preferences = context.getSharedPreferences(Constant.Preferences.SESSION, Context.MODE_PRIVATE)
    private val authAdapter = moshi.adapter(Auth::class.java)
    private val authSubject = PublishSubject.create<Auth>()
    private val sessionSubject = PublishSubject.create<Session>()
    private val sessionAdapter = moshi.adapter(Session::class.java)

    val sessionObservable: Observable<Session> = sessionSubject

    val isLoggedIn: Boolean
        get() = auth != null && (!TextUtils.isEmpty(auth!!.token))

    var auth: Auth?
        @Synchronized
        set(value) {
            if (value == null) {
                preferences.edit().remove(Constant.Key.AUTH).apply()
            } else {
                preferences.saveString(Constant.Key.AUTH, authAdapter.toJson(value))
                authSubject.onNext(value)
            }
        }
        get() {
            return try {
                authAdapter.fromJson(preferences.getString(Constant.Key.AUTH, ""))
            } catch (error: Exception) {
                Log.e(TAG, error.message)
                null
            }
        }

    fun clear() = preferences.edit().clear().apply()
}


