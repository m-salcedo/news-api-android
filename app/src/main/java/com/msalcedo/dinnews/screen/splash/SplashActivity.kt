package com.msalcedo.dinnews.screen.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msalcedo.dinnews.MainActivity
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.screen.splash.di.DaggerSplashComponent
import com.msalcedo.dinnews.screen.splash.di.SplashModule
import com.msalcedo.dinnews.screen.splash.mvvm.SplashViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        viewModel.start()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ MainActivity.start(this) })
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
        fun start(context: Context) {
            val intent = context.intentFor<SplashActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
