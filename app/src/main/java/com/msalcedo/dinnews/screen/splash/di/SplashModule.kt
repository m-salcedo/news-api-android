package com.msalcedo.dinnews.screen.splash.di

import android.arch.lifecycle.ViewModelProviders
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.screen.splash.SplashActivity
import com.msalcedo.dinnews.screen.splash.mvvm.SplashContract
import com.msalcedo.dinnews.screen.splash.mvvm.SplashView
import com.msalcedo.dinnews.screen.splash.mvvm.SplashViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@Module
class SplashModule(private val activity: SplashActivity) {

    @Provides
    @SplashScope
    fun provideSplashActivity(): SplashActivity = activity

    @Provides
    @SplashScope
    fun provideView(viewModel: SplashViewModel): SplashContract.View = SplashView(activity, viewModel)

    @Provides
    @SplashScope
    fun provideViewModel(api: InterfaceApi): SplashViewModel {
        val viewModel = ViewModelProviders.of(activity).get(SplashViewModel::class.java)
        viewModel.init(api)
        return viewModel
    }
}