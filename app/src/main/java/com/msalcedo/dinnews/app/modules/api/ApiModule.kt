package com.msalcedo.dinnews.app.modules.api

import com.msalcedo.dinnews.app.di.AppScope
import com.msalcedo.dinnews.app.di.AuthenticationQualifier
import com.msalcedo.dinnews.app.modules.network.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module(includes = [(RetrofitModule::class)])
class ApiModule {

    @Provides
    @AppScope
    fun provideApi(@AuthenticationQualifier retrofit: Retrofit): InterfaceApi = retrofit.create(InterfaceApi::class.java)

}