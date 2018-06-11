package com.msalcedo.dinnews.screen.home.di

import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.screen.home.HomeActivity
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@HomeScope
@Component(dependencies = [(AppComponent::class)], modules = [(HomeModule::class)])
interface HomeComponent {
    fun inject(activity: HomeActivity): Unit
}