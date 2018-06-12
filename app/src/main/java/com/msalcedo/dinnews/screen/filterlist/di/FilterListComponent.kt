package com.msalcedo.dinnews.screen.filterlist.di

import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.screen.filterlist.FilterListActivity
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@FilterListScope
@Component(dependencies = [(AppComponent::class)], modules = [(FilterListModule::class)])
interface FilterListComponent {
    fun inject(activity: FilterListActivity): Unit
}