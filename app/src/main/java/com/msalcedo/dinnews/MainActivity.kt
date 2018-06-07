package com.msalcedo.dinnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.intentFor
import timber.log.Timber

class MainActivity : RxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Timber.d("testing")

        addDisposable(
                Application.component.api().everything()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ Timber.d("testiing") }, { }))
    }

    companion object {
        fun start(context: Context) {
            val intent = context.intentFor<MainActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
