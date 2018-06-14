package com.msalcedo.dinnews.screen.detail.mvvm

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.Article

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/11/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class DetailViewModel : ViewModel(), DetailContract.ViewModel {

    private val TAG = "TAG_${DetailViewModel::class.java.simpleName}"

    var article: Article? = null

    fun init(api: InterfaceApi) {
    }

    fun start(extras: Bundle) {
        article = Article.adapter.fromJson(extras.getString(Article.KEY))
    }

}