package com.msalcedo.dinnews.common.mvp

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import com.msalcedo.dinnews.common.RxActivity

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
abstract class MVPContract {

    interface Model

    interface View {
        val activity: RxActivity
        fun inflateLayout(container: ViewGroup? = null): android.view.View?
        fun showToast(message: String, longTime: Boolean = true)
        fun showToast(message: Int, longTime: Boolean = true)
        fun showProgress(message: String, cancelable: Boolean = false)
        fun showProgress(message: Int, cancelable: Boolean = false)
        fun hideProgress()
        fun showConfirmation(title: Int, message: Int, listener: DialogInterface.OnClickListener): AlertDialog?
    }

    interface Presenter {
        val view: View
        val model: Model
        fun onCreate()
        fun onDestroy()
    }
}