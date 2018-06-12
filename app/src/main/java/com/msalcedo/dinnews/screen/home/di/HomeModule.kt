package com.msalcedo.dinnews.screen.home.di

import android.arch.lifecycle.ViewModelProviders
import com.msalcedo.dinnews.screen.home.HomeActivity
import com.msalcedo.dinnews.screen.home.mvvm.HomeContract
import com.msalcedo.dinnews.screen.home.mvvm.HomeView
import com.msalcedo.dinnews.screen.home.mvvm.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

@Module
class HomeModule(private val activity: HomeActivity) {

    @Provides
    @HomeScope
    fun provideHomeActivity(): HomeActivity = activity

    @Provides
    @HomeScope
    fun provideView(viewModel: HomeViewModel): HomeContract.View = HomeView(activity, viewModel)

    @Provides
    @HomeScope
    fun provideViewModel(): HomeViewModel = ViewModelProviders.of(activity).get(HomeViewModel::class.java)
}