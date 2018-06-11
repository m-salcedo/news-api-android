package com.msalcedo.dinnews.screen.filter.di

import android.arch.lifecycle.ViewModelProviders
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.screen.filter.FilterFragment
import com.msalcedo.dinnews.screen.filter.mvvm.FilterContract
import com.msalcedo.dinnews.screen.filter.mvvm.FilterView
import com.msalcedo.dinnews.screen.filter.mvvm.FilterViewModel
import com.msalcedo.dinnews.screen.home.HomeActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module
class FilterModule(private  val  fragment: FilterFragment) {

    @Provides
    @FilterScope
    fun provideHomeActivity(): HomeActivity = fragment.activity as HomeActivity

    @Provides
    @FilterScope
    fun provideFilterListFragment(): FilterFragment = fragment

    @Provides
    @FilterScope
    fun provideView(viewModel: FilterViewModel, activity: HomeActivity): FilterContract.View = FilterView(activity, viewModel)

    @Provides
    @FilterScope
    fun provideViewModel(api: InterfaceApi): FilterViewModel {
        val viewModel = ViewModelProviders.of(fragment).get(FilterViewModel::class.java)
        viewModel.init(api)
        return viewModel
    }
}