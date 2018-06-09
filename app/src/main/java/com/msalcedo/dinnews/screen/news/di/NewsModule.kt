package com.msalcedo.dinnews.screen.news.di

import android.arch.lifecycle.ViewModelProviders
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.screen.home.HomeActivity
import com.msalcedo.dinnews.screen.news.NewsListFragment
import com.msalcedo.dinnews.screen.news.mvvm.NewsContract
import com.msalcedo.dinnews.screen.news.mvvm.NewsView
import com.msalcedo.dinnews.screen.news.mvvm.NewsViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

@Module
class NewsModule(private  val  fragment: NewsListFragment) {

    @Provides
    @NewsScope
    fun provideHomeActivity(): HomeActivity = fragment.activity as HomeActivity

    @Provides
    @NewsScope
    fun provideNewsListFragment(): NewsListFragment = fragment

    @Provides
    @NewsScope
    fun provideView(viewModel: NewsViewModel, activity: HomeActivity): NewsContract.View = NewsView(activity, viewModel)

    @Provides
    @NewsScope
    fun provideViewModel(api: InterfaceApi): NewsViewModel {
        val viewModel = ViewModelProviders.of(fragment).get(NewsViewModel::class.java)
        return viewModel
    }
}