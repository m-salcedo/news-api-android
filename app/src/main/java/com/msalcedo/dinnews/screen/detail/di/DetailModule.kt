package com.msalcedo.dinnews.screen.detail.di

import android.arch.lifecycle.ViewModelProviders
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.screen.detail.DetailActivity
import com.msalcedo.dinnews.screen.detail.mvvm.DetailContract
import com.msalcedo.dinnews.screen.detail.mvvm.DetailView
import com.msalcedo.dinnews.screen.detail.mvvm.DetailViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/11/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module
class DetailModule(private val activity: DetailActivity) {

    @Provides
    @DetailScope
    fun provideDetailActivity(): DetailActivity = activity

    @Provides
    @DetailScope
    fun provideView(viewModel: DetailViewModel): DetailContract.View = DetailView(activity, viewModel)

    @Provides
    @DetailScope
    fun provideViewModel(api: InterfaceApi): DetailViewModel {
        val viewModel = ViewModelProviders.of(activity).get(DetailViewModel::class.java)
        viewModel.init(api)
        return viewModel
    }
}