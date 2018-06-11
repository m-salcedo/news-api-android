package com.msalcedo.dinnews.screen.detail.di

import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.screen.detail.DetailActivity
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/11/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@DetailScope
@Component(dependencies = [(AppComponent::class)], modules = [(DetailModule::class)])
interface DetailComponent {
    fun inject(activity: DetailActivity): Unit
}