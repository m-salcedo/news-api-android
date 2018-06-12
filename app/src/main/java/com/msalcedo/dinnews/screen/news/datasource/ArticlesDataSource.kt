package com.msalcedo.dinnews.screen.news.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.msalcedo.dinnews.app.modules.api.InterfaceApi
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.models.ResponseArticle
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ArticlesDataSource(
        private val api: InterfaceApi,
        private val compositeDisposable: CompositeDisposable,
        private val filter: Filter,
        private val top: Boolean)
    : ItemKeyedDataSource<String, Article>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    var page = 1
    var pageTotal: Long? = null

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Timber.d("testing here")
                    }, { throwable -> Timber.e(throwable.message) }))
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Article>) {
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        //get the initial Articles from the api


        compositeDisposable.add(findArticles(page, filter).subscribe({ result ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)

            pageTotal = (result.totalResults?.div(20))
            result.articles?.let { callback.onResult(it) }
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkState.error(throwable.message)
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Article>) {
        // set network value to loading.
        networkState.postValue(NetworkState.LOADING)

        page++

        if (pageTotal != null && page > pageTotal!!) return

        //get the Articles from the api after id
        compositeDisposable.add(findArticles(page, filter).subscribe({ result ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(result.articles!!)
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadAfter(params, callback) })
            // publish the error
            networkState.postValue(NetworkState.error(throwable.message))
        }))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Article>) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: Article): String {
        return item.url!!
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

    private fun findArticles(page: Int, filter: Filter): Single<ResponseArticle> {
        return if (top) topHeadlines(page, filter) else everything(page, filter)
    }

    private fun everything(page: Int, filter: Filter): Single<ResponseArticle> {
        return api.everything(page,
                filter.sources.id,
                filter.getLanguageDefault(),
                filter.from,
                filter.to,
                filter.getKeyWord(),
                filter.sortBy!!.id)
    }

    private fun topHeadlines(page: Int, filter: Filter): Single<ResponseArticle> {
        return api.topHeadlines(page,
                filter.sources.id,
                filter.category!!.id,
                filter.country!!.id,
                filter.getLanguageDefault(),
                filter.getKeyWord(),
                filter.sortBy!!.id)
    }
}