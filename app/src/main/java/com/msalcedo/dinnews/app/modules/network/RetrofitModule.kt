package com.msalcedo.dinnews.app.modules.network

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.di.AppScope
import com.msalcedo.dinnews.app.di.AuthenticationQualifier
import com.msalcedo.dinnews.app.di.BaseUrlQualifier
import com.msalcedo.dinnews.app.di.FlatObjectsQualifier
import com.msalcedo.dinnews.utils.CollectionTypedAdapter
import com.msalcedo.dinnews.utils.DateTimeConverter
import com.msalcedo.dinnews.utils.DoubleTypedAdapter
import com.msalcedo.dinnews.utils.LongTypedAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module(includes = arrayOf(NetworkModule::class))
class RetrofitModule {

    @Provides
    @AppScope
    @BaseUrlQualifier
    fun provideBaseUrl(resources: Resources): String = resources.getString(R.string.url_api_current)

    @Provides
    @AppScope
    fun provideConverterFactory(@FlatObjectsQualifier moshi: Moshi): Converter.Factory =
            MoshiConverterFactory.create(moshi)

    @Provides
    @AppScope
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @AppScope
    @AuthenticationQualifier
    fun provideAuthenticatedRetrofit(
            @BaseUrlQualifier baseUrl: String,
            @AuthenticationQualifier client: OkHttpClient,
            converter: Converter.Factory,
            callAdapter: CallAdapter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(callAdapter)
                    .build()

    @Provides
    @AppScope
    fun provideRetrofit(
            @BaseUrlQualifier baseUrl: String,
            client: OkHttpClient,
            converter: Converter.Factory,
            callAdapter: CallAdapter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(callAdapter)
                    .build()
}
