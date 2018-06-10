package com.msalcedo.dinnews.screen.home

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.AdapterView
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
    }

    override fun initPortrait() {
        mountFragment(NewsListFragment.newInstance(), R.id.container)
    }

    override fun initLandscape() {
        mountFragment(NewsListFragment.newInstance(), R.id.containerRight)
        mountFragment(FilterFragment.newInstance(), R.id.containerLeft)
    }

    private fun mountFragment(fragment: Fragment, containter: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containter, fragment)
        transaction.commit()
    }

    override fun onSearch(filter: Filter) {
        Timber.d("Search $filter")
    }

    override fun onClickSearch() {
        mountFragment(FilterFragment.newInstance(), R.id.container)
    }

    override fun initializeComponent() {
        DaggerHomeComponent.builder()
                .homeModule(
                        HomeModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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
