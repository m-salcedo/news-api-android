package com.msalcedo.dinnews.screen.news.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.os.Bundle
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.screen.news.datasource.ArticlesDataSource
import com.msalcedo.dinnews.screen.news.datasource.ArticlesDataSourceFactory
import com.msalcedo.dinnews.screen.news.datasource.NetworkState
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class NewsViewModel : ViewModel(), NewsContract.ViewModel {

    private val TAG = "TAG_${NewsViewModel::class.java.simpleName}"


    lateinit var articleList: LiveData<PagedList<Article>>

    lateinit var articleListTop: LiveData<PagedList<Article>>

    private var api: InterfaceApi = Application.component.api()

    private val compositeDisposable = CompositeDisposable()

    private var filter: Filter = Filter()

    private val pageSize = 20

    private lateinit var sourceFactoryTop: ArticlesDataSourceFactory

    private lateinit var sourceFactory: ArticlesDataSourceFactory


    fun setBundle(bundle: Bundle) {

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()

        filter = Filter.adapter.fromJson(bundle.getString(Filter.KEY, "{}"))

        sourceFactoryTop = ArticlesDataSourceFactory(compositeDisposable, api, filter, true)

        articleListTop = LivePagedListBuilder<String, Article>(sourceFactoryTop, config).build()

        sourceFactory = ArticlesDataSourceFactory(compositeDisposable, api, filter, false)

        articleList = LivePagedListBuilder<String, Article>(sourceFactory, config).build()
    }

    fun retry() {
        sourceFactory.articlesDataSourceLiveData.value!!.retry()
    }

    fun refresh() {
        sourceFactory.articlesDataSourceLiveData.value!!.invalidate()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<ArticlesDataSource, NetworkState>(
            sourceFactory.articlesDataSourceLiveData, { it.networkState })

    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<ArticlesDataSource, NetworkState>(
            sourceFactory.articlesDataSourceLiveData, { it.initialLoad })


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getKeyWord(): CharSequence? {
        return filter.q
    }

}