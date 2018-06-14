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
import com.msalcedo.dinnews.screen.detail.DetailActivity
import com.msalcedo.dinnews.screen.filter.FilterFragment
import com.msalcedo.dinnews.screen.home.di.DaggerHomeComponent
import com.msalcedo.dinnews.screen.home.di.HomeModule
import com.msalcedo.dinnews.screen.home.mvvm.HomeViewModel
import com.msalcedo.dinnews.screen.news.NewsListFragment
import org.jetbrains.anko.intentFor
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

    override fun initPortrait() {
        super.initPortrait()
        if (viewModel.starting) {
            mountFilter()
            viewModel.starting = false
        } else {
            mountList()
        }
    }

    override fun initLandscape() {
        super.initLandscape()
        mountList()
        mountFragment(FilterFragment.instance(viewModel.getFilter(), viewModel.starting), R.id.containerLeft)
        viewModel.starting = false
    }

    private fun mountFragment(fragment: Fragment, containter: Int) {
        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction().replace(containter, fragment).commit()
    }

    private fun mountList() {
        mountFragment(NewsListFragment.newInstance(viewModel.getFilter()), R.id.container)
    }

    private fun mountFilter() {
        mountFragment(FilterFragment.instance(viewModel.getFilter(), viewModel.starting), R.id.container)
    }

    override fun search(filter: Filter) {
        viewModel.setFilter(filter)
        mountList()
    }

    override fun onClickSearch() {
        mountFilter()
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
        DetailActivity.start(this, article)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        FilterFragment.instance().onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (viewModel.landscapeMode() || !FilterFragment.instance().isVisible) {
            super.onBackPressed()
        } else {
            mountList()
        }
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
