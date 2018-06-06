package com.msalcedo.dinnews.app.modules

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.msalcedo.dinnews.app.di.AppModule
import com.msalcedo.dinnews.app.di.AppQualifier
import com.msalcedo.dinnews.app.di.AppScope
import com.msalcedo.dinnews.app.modules.network.NetworkModule
import com.squareup.picasso.Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module(includes = arrayOf(NetworkModule::class, AppModule::class))
class PicassoModule {

    @Provides
    @AppScope
    fun provideDownloader(okHttpClient: OkHttpClient): Downloader = OkHttp3Downloader(okHttpClient)

    @Provides
    @AppScope
    fun providePicasso(@AppQualifier context: Context, downloader: Downloader): Picasso =
            Picasso.Builder(context)
                    .downloader(downloader)
                    .build()
}