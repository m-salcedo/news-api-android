package com.msalcedo.dinnews.screen.news

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxFragment
import com.msalcedo.dinnews.databinding.FragmentNewsListBinding
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.screen.news.adapter.ArticleAdapter
import com.msalcedo.dinnews.screen.news.datasource.NetworkState
import com.msalcedo.dinnews.screen.news.datasource.Status
import com.msalcedo.dinnews.screen.news.di.DaggerNewsComponent
import com.msalcedo.dinnews.screen.news.di.NewsModule
import com.msalcedo.dinnews.screen.news.events.NewListEvent
import com.msalcedo.dinnews.screen.news.mvvm.NewsViewModel
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class NewsListFragment : RxFragment(), NewListEvent {

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var binding: FragmentNewsListBinding

    @Inject
    lateinit var viewModel: NewsViewModel
    private lateinit var articleAdapter: ArticleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setBundle(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false)
        this.binding.eventHandler = this

        super.onCreateView(inflater, container, savedInstanceState)

        return this.binding.root
    }

    override fun init() {
        Timber.d("test " + context?.getString(R.string.api_key_news))

        binding.tvBox.text = viewModel.getKeyWord()
        initAdapter()
        initSwipeToRefresh()
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        articleAdapter = ArticleAdapter({ viewModel.retry() }, listener!!)
        binding.recyclerNews.layoutManager = linearLayoutManager
        binding.recyclerNews.adapter = articleAdapter

        viewModel.articleListTop.observe(this, Observer<PagedList<Article>> {
            var resultTop = it
            viewModel.articleList.observe(this, Observer<PagedList<Article>> {

                var resultList = it

                if ((resultList == null || resultList.isEmpty()) && resultTop != null) {
                    resultList = resultTop
                    resultTop = null
                }

                articleAdapter.submitList(resultList)
                articleAdapter.listTopHeadlines(resultTop)
                binding.recyclerNews.scrollToPosition(0)

                if (resultList == null || resultList.isEmpty()) {
                    binding.emptyView.visibility = View.VISIBLE
                } else {
                    binding.emptyView.visibility = View.GONE

                }
            })
        })
    }

    override fun initLandscape() {
        super.initLandscape()
        binding.llBox.visibility = View.GONE
    }
    private fun initSwipeToRefresh() {
        viewModel.getRefreshState().observe(this, Observer { networkState ->
            if (articleAdapter.currentList != null) {
                if (articleAdapter.currentList!!.size > 0) {
                    binding.swipeNews.isRefreshing = networkState?.status == NetworkState.LOADING.status
                } else {
                    setInitialLoadingState(networkState)
                }
            } else {
                setInitialLoadingState(networkState)
            }
        })
        binding.swipeNews.setOnRefreshListener({ viewModel.refresh() })
    }

    private fun setInitialLoadingState(networkState: NetworkState?) {
        binding.swipeNews.isEnabled = networkState?.status == Status.SUCCESS
    }

    override fun onClickSearch() {
        listener?.onClickSearch()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener : ArticleAdapter.OnArticleSelected {
        fun onClickSearch()
    }

    override fun initializeComponent() {
        DaggerNewsComponent.builder()
                .newsModule(
                        NewsModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    companion object {
        val KEY: String = NewsListFragment.javaClass.name

        @JvmStatic
        fun newInstance(filter: Filter) =
                NewsListFragment().apply {
                    arguments = Bundle().apply {
                        putString(Filter.KEY, Filter.adapter.toJson(filter))
                    }
                }
    }
}
