package com.msalcedo.dinnews.screen.home

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.databinding.ActivityHomeBinding
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.screen.filter.FilterFragment
import com.msalcedo.dinnews.screen.home.di.DaggerHomeComponent
import com.msalcedo.dinnews.screen.home.di.HomeModule
import com.msalcedo.dinnews.screen.home.mvvm.HomeViewModel
import com.msalcedo.dinnews.screen.news.NewsListFragment
import org.jetbrains.anko.intentFor
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/7/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class HomeActivity : RxActivity(),
        NewsListFragment.OnListFragmentInteractionListener,
        FilterFragment.OnFragmentInteractionListener {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun init() {
        viewModel.start()
        mountFragment(NewsListFragment.newInstance(viewModel.getFilter()), R.id.container)
    }

    override fun initLandscape() {
        mountFragment(FilterFragment.newInstance(viewModel.getFilter()), R.id.containerLeft)
    }

    private fun mountFragment(fragment: Fragment, containter: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containter, fragment)
        transaction.commit()
    }

    override fun search(filter: Filter) {
        viewModel.setFilter(filter)
        mountFragment(NewsListFragment.newInstance(viewModel.getFilter()), R.id.container)
    }

    override fun onClickSearch() {
        mountFragment(FilterFragment.newInstance(viewModel.getFilter()), R.id.container)
    }

    override fun initializeComponent() {
        DaggerHomeComponent.builder()
                .homeModule(
                        HomeModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    override fun onArticleSelected(article: Article) {
        Timber.d("Item selected: $article")
    }

    companion object {
        fun start(context: Context) {
            val intent = context.intentFor<HomeActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
