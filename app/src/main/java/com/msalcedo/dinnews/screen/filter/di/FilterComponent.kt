package com.msalcedo.dinnews.screen.filter.di

import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.screen.filter.FilterFragment
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@FilterScope
@Component(dependencies = [(AppComponent::class)], modules = [(FilterModule::class)])
interface FilterComponent {
    fun inject(fragment: FilterFragment): Unit
}