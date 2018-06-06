package com.msalcedo.dinnews.app.di

import android.content.Context
import android.content.res.Resources
import com.msalcedo.dinnews.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @AppScope
    fun provideApp(): Application = app

    @Provides
    @AppScope
    @AppQualifier
    fun provideApplicationContext(): Context = app

    @Provides
    @AppScope
    fun provideResources(): Resources = app.resources
}