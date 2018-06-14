package com.msalcedo.dinnews.screen.news.di

import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.screen.news.NewsListFragment
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@NewsScope
@Component(dependencies = [(AppComponent::class)], modules = [(NewsModule::class)])
interface NewsComponent {
    fun inject(fragment: NewsListFragment): Unit
}