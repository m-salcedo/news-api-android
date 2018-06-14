package com.msalcedo.dinnews.common.mvp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.FrameLayout
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.utils.showConfirmation
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
abstract class MVVMView<ViewModel>(override val activity: RxActivity): FrameLayout(activity), MVPContract.View<ViewModel> {

    private var progressDialog: ProgressDialog? = null

    override fun showProgress(message: Int, cancelable: Boolean) {
        progressDialog = activity.indeterminateProgressDialog(message)
        progressDialog?.setCancelable(cancelable)
    }

    override fun showProgress(message: String, cancelable: Boolean) {
        progressDialog = activity.indeterminateProgressDialog(message)
        progressDialog?.setCancelable(cancelable)
    }

    override fun hideProgress() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    override fun showToast(message: Int, longTime: Boolean) {
        if (longTime) activity.longToast(message)
        else activity.toast(message)
    }

    override fun showToast(message: String, longTime: Boolean) {
        if (longTime) activity.longToast(message)
        else activity.toast(message)
    }

    override fun showConfirmation(title: Int, message: Int, listener: DialogInterface.OnClickListener): AlertDialog? {
        return showConfirmation(activity, title, message, listener)
    }
}