package com.msalcedo.dinnews.common

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Call
import retrofit2.Response


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/6/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class CallExecuteSingle<T>(originalCall: Call<T>) : Single<Response<T>>() {

    private val TAG = "TAG_${CallExecuteSingle::class.java.simpleName}"

    private var originalCall: Call<T>? = originalCall


    override fun subscribeActual(observer: SingleObserver<in Response<T>>) {
        val call = originalCall!!.clone()
        observer.onSubscribe(CallDisposable(call))

        var terminated = false
        try {
            val response = call.execute()
            if (!call.isCanceled) {
                terminated = true
                observer.onSuccess(response)
            } else {
                terminated = true
                observer.onError(Throwable())
            }
        } catch (t: Throwable) {
            Exceptions.throwIfFatal(t)
            if (terminated) {
                RxJavaPlugins.onError(t)
                observer.onError(t)
            } else if (!call.isCanceled) {
                try {
                    observer.onError(t)
                } catch (inner: Throwable) {
                    Exceptions.throwIfFatal(inner)
                    RxJavaPlugins.onError(CompositeException(t, inner))
                }

            }
        }
    }

    private class CallDisposable internal constructor(private val call: Call<*>) : Disposable {

        override fun dispose() {
            call.cancel()
        }

        override fun isDisposed(): Boolean {
            return call.isCanceled
        }
    }

}