package com.msalcedo.dinnews.app

import android.support.multidex.MultiDexApplication
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.di.AppComponent
import com.msalcedo.dinnews.app.di.AppModule
import com.msalcedo.dinnews.app.di.DaggerAppComponent
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class Application : MultiDexApplication() {

    private val TAG = "TAG_${Application::class.java.simpleName}"

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        daggerConfig()
        calligraphyConfig()
        timberConfig()
    }

    private fun daggerConfig() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        component.inject(this)
    }

    private fun calligraphyConfig() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    private fun timberConfig() {
        Timber.plant(
                object : Timber.DebugTree() {
                    // Add the line number to the tag.
                    override fun createStackElementTag(element: StackTraceElement): String? {
                        return super.createStackElementTag(element) + ':'.toString() + element.lineNumber
                    }
                })
    }
}