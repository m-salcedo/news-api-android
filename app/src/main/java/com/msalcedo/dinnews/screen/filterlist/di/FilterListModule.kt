package com.msalcedo.dinnews.screen.filterlist.di

import android.arch.lifecycle.ViewModelProviders
import com.msalcedo.dinnews.screen.filterlist.FilterListActivity
import com.msalcedo.dinnews.screen.filterlist.mvvm.FilterListContract
import com.msalcedo.dinnews.screen.filterlist.mvvm.FilterListView
import com.msalcedo.dinnews.screen.filterlist.mvvm.FilterListViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module
class FilterListModule(private val activity: FilterListActivity) {

    @Provides
    @FilterListScope
    fun provideFilterListActivity(): FilterListActivity = activity

    @Provides
    @FilterListScope
    fun provideView(viewModel: FilterListViewModel): FilterListContract.View = FilterListView(activity, viewModel)

    @Provides
    @FilterListScope
    fun provideViewModel(): FilterListViewModel {
        val viewModel = ViewModelProviders.of(activity).get(FilterListViewModel::class.java)
        viewModel.init(activity)
        return viewModel
    }
}