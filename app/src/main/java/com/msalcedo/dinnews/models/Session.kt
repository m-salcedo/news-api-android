package com.msalcedo.dinnews.models

import android.os.Parcel
import android.os.Parcelable
import com.msalcedo.dinnews.common.ext.createParcel

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
data class Session(
        val id: String? = null,
        val createdAt: String? = null
): Parcelable {

    companion object {
        @JvmField
        val CREATOR = createParcel {
            val id = it.readString()
            val createdAt = it.readString()

            Session(
                    id = id,
                    createdAt = createdAt)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(createdAt)
    }
}