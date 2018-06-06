package com.msalcedo.dinnews.app.modules.network

import android.content.Context
import com.msalcedo.dinnews.BuildConfig
import com.msalcedo.dinnews.app.di.*
import com.msalcedo.dinnews.utils.Constant
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module(includes = [(AppModule::class)])
class NetworkModule {

    @Provides
    @AppScope
    @CacheQualifier
    fun provideCacheFile(@AppQualifier context: Context): File =
            File(context.cacheDir, Constant.Preferences.CACHE)

    @Provides
    @AppScope
    @CacheQualifier
    fun provideCacheMaxSize(): Long = 10 * 1024 * 1024

    @Provides
    @AppScope
    fun provideCache(@CacheQualifier file: File, @CacheQualifier maxSize: Long): Cache =
            Cache(file, maxSize)

    @Provides
    @AppScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @AppScope
    @AuthenticationQualifier
    fun provideAuthenticatedOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            authenticationInterceptor: AuthenticationInterceptor,
            cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(authenticationInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()

    @Provides
    @AppScope
    fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
}
