package com.msalcedo.dinnews.app.di

import android.content.res.Resources
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.app.modules.MoshiModule
import com.msalcedo.dinnews.app.modules.PicassoModule
import com.msalcedo.dinnews.app.modules.api.ApiModule
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@AppScope
@Component(modules = [(PicassoModule::class), (MoshiModule::class), (ApiModule::class)])
interface AppComponent {
    fun inject(app: Application): Unit
    fun picasso(): Picasso
    fun moshi(): Moshi
    fun resources(): Resources
    fun api(): InterfaceApi

}