package com.msalcedo.dinnews.utils

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.msalcedo.dinnews.R

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
fun showConfirmation(context: Context, title: Int, message: Int, listener: DialogInterface.OnClickListener): AlertDialog? {
    return AlertDialog.Builder(context)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes, listener)
            .setNegativeButton(R.string.no, null)
            .show()
}
