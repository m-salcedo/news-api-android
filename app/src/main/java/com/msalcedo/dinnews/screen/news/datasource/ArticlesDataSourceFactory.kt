package com.msalcedo.dinnews.screen.news.datasource

import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import io.reactivex.disposables.CompositeDisposable
import android.arch.paging.DataSource
import android.arch.lifecycle.MutableLiveData
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.models.Filter

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ArticlesDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                private val api: InterfaceApi,
                                private val filter: Filter,
                                private val top: Boolean)
    : DataSource.Factory<String, Article>() {

    val articlesDataSourceLiveData = MutableLiveData<ArticlesDataSource>()

    override fun create(): DataSource<String, Article> {
        val articlesDataSource = ArticlesDataSource(api, compositeDisposable, filter, top)
        articlesDataSourceLiveData.postValue(articlesDataSource)
        return articlesDataSource
    }

}