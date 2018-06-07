package com.msalcedo.dinnews.screen.splash.di

import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.screen.splash.SplashActivity
import dagger.Component

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
@SplashScope
@Component(dependencies = [(AppComponent::class)], modules = [(SplashModule::class)])
interface SplashComponent {
    fun inject(activity: SplashActivity): Unit
}