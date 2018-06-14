package com.msalcedo.dinnews.app.modules

import com.msalcedo.dinnews.app.di.AppScope
import com.msalcedo.dinnews.app.di.FlatObjectsQualifier
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module
class MoshiModule {
    @Provides
    @AppScope
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @AppScope
    @FlatObjectsQualifier
    fun provideMoshiFlatObjects(): Moshi {
        return Moshi.Builder()
                .build()
    }
}
