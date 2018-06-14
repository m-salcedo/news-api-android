package com.msalcedo.dinnews.screen.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.screen.home.HomeActivity
import com.msalcedo.dinnews.screen.splash.di.DaggerSplashComponent
import com.msalcedo.dinnews.screen.splash.di.SplashModule
import com.msalcedo.dinnews.screen.splash.mvvm.SplashViewModel
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class SplashActivity : RxActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun init() {
        addDisposable(viewModel.start()
                .subscribe({

                    HomeActivity.start(this)
                }, { }))
    }

    override fun initializeComponent() {
        DaggerSplashComponent.builder()
                .splashModule(
                        SplashModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    companion object {
        const val KEY: String = "starting"

        fun start(context: Context) {
            val intent = context.intentFor<SplashActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
