package com.msalcedo.dinnews.screen.filterlist

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxActivity
import com.msalcedo.dinnews.databinding.ActivityFilterListBinding
import com.msalcedo.dinnews.models.Source
import com.msalcedo.dinnews.screen.filterlist.adapter.FilterAdapter
import com.msalcedo.dinnews.screen.filterlist.di.DaggerFilterListComponent
import com.msalcedo.dinnews.screen.filterlist.di.FilterListModule
import com.msalcedo.dinnews.screen.filterlist.mvvm.FilterListViewModel
import javax.inject.Inject

class FilterListActivity : RxActivity(), FilterAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModel: FilterListViewModel
    private lateinit var binding: ActivityFilterListBinding

    private lateinit var viewAdapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_list)
        initView()
    }

    private fun initView() {
        initRecycler()
        setData()
        initToolbar()
    }

    private fun setData() {
        viewAdapter = FilterAdapter(viewModel.getList(), this)
        binding.recycler.adapter = viewAdapter
        viewAdapter.notifyDataSetChanged()
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = linearLayoutManager
        binding.recycler.setHasFixedSize(true)
    }

    override fun init() {
        viewModel.start(intent.extras)
    }

    private fun initToolbar() {
        binding.toolbar.title = ""
        binding.toolbar.setNavigationOnClickListener({
            onBackPressed()
        })
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun onClickItem(view: View, source: Source) {
        viewModel.setSelected(source)
        finish()
    }

    override fun initializeComponent() {
        DaggerFilterListComponent.builder()
                .filterListModule(
                        FilterListModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)

    }

    override fun finish() {
        setResult(viewModel.result(), viewModel.getSelected())
        super.finish()
    }

    companion object {
        const val FILTER_LIST_REQUEST = "filter_type"

        const val FILTER_CATEGORIES = 1
        const val FILTER_COUNTRIES = 2
        const val FILTER_LANGUAGES = 3
        const val FILTER_SORT_BY = 4
        const val FILTER_SOURCES = 5


        fun start(activity: Activity, int: Int) {
            val intent = Intent(activity, FilterListActivity::class.java)
            intent.putExtra(FILTER_LIST_REQUEST, int)
            activity.startActivityForResult(intent, int)
        }
    }
}
