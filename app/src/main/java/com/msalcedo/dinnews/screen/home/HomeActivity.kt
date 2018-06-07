package com.msalcedo.dinnews.screen.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msalcedo.dinnews.MainActivity
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.screen.home.di.DaggerHomeComponent
import com.msalcedo.dinnews.screen.home.di.HomeModule
import com.msalcedo.dinnews.screen.home.mvvm.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class HomeActivity : RxActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun init() {
        viewModel.start()
    }

    override fun initializeComponent() {
        DaggerHomeComponent.builder()
                .homeModule(
                        HomeModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    companion object {
        fun start(context: Context) {
            val intent = context.intentFor<HomeActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
