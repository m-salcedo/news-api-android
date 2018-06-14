package com.msalcedo.dinnews.app.modules.network

import android.content.Context
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.di.AppQualifier
import com.msalcedo.dinnews.app.di.AppScope
import com.msalcedo.dinnews.utils.Constant
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@AppScope
class AuthenticationInterceptor @Inject constructor(
        @AppQualifier val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {
        val newRequest = chain.request().newBuilder()
                .addHeader(Constant.Key.AUTHORIZATION,
                        Constant.Key.BEARER + context.getString(R.string.api_key_news))
                .build()
        return chain.proceed(newRequest)
    }
}