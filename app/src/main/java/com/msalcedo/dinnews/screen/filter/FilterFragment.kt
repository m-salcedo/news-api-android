package com.msalcedo.dinnews.screen.filter

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxFragment
import com.msalcedo.dinnews.common.ext.empty
import com.msalcedo.dinnews.databinding.FragmentFilterBinding
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.screen.filter.di.DaggerFilterComponent
import com.msalcedo.dinnews.screen.filter.di.FilterModule
import com.msalcedo.dinnews.screen.filter.mvvm.FilterViewModel
import com.msalcedo.dinnews.screen.filterlist.FilterListActivity
import com.msalcedo.dinnews.screen.news.events.NewListEvent
import com.msalcedo.dinnews.screen.splash.SplashActivity
import org.joda.time.DateTime
import javax.inject.Inject


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class FilterFragment : RxFragment(), NewListEvent {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var binding: FragmentFilterBinding

    @Inject
    lateinit var viewModel: FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setBundle(arguments!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false)
        this.binding.eventHandler = this

        super.onCreateView(inflater, container, savedInstanceState)

        if (viewModel.starting) {
            FilterListActivity.start(activity!!, FilterListActivity.FILTER_SOURCES)
        }

        return this.binding.root
    }

    override fun init() {
        initBoxSearch()
        initPicketDate()
        initCategories()
        initSource()
        initLanguages()
        initCountries()
        initSort()
    }

    private fun initBoxSearch() {
        binding.etBox.setText(viewModel.getKeyWord())
        val pos = binding.etBox.text.toString().length
        binding.etBox.setSelection(pos)
    }

    private fun initPicketDate() {
        binding.tvFrom.text = viewModel.getFromDate()
        binding.tvTo.text = viewModel.getToDate()

        binding.tvFrom.setOnClickListener {
            fromDatePickerShow()
        }
        binding.tvTo.setOnClickListener {
            toDatePickerShow()
        }
    }

    private fun fromDatePickerShow() {
        val currentYear: Int
        val currentMonth: Int
        val currentDay: Int

        var time: DateTime = DateTime.now()

        if (!viewModel.getFromDate().empty()) {
            time = viewModel.parserDate().parseDateTime(viewModel.getFromDate())
        }

        currentYear = time.year
        currentMonth = time.monthOfYear - 1
        currentDay = time.dayOfMonth

        DatePickerDialog(context, fromListener, currentYear, currentMonth, currentDay).show()
    }

    private fun toDatePickerShow() {
        val currentYear: Int
        val currentMonth: Int
        val currentDay: Int

        var time: DateTime = DateTime.now()

        if (!viewModel.getToDate().empty()) {
            time = viewModel.parserDate().parseDateTime(viewModel.getToDate())
        }

        currentYear = time.year
        currentMonth = time.monthOfYear - 1
        currentDay = time.dayOfMonth

        DatePickerDialog(context, toListener, currentYear, currentMonth, currentDay).show()
    }

    private val fromListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

        var monthStr = month
        monthStr++

        binding.tvFrom.text = ("$year-$monthStr-$dayOfMonth")
        viewModel.setDateFrom(binding.tvFrom.text.toString())
        if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
    }

    private val toListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        var monthStr = month
        monthStr++

        binding.tvTo.text = ("$year-$monthStr-$dayOfMonth")
        viewModel.setDateTo(binding.tvTo.text.toString())
        if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
    }

    override fun initLandscape() {
        super.initLandscape()
        binding.btn.visibility = View.GONE

        listenerSearch()
    }

    private fun initSort() {
        binding.tvSortBy.text = (viewModel.getSortBy())

        binding.tvSortBy.setOnClickListener {
            FilterListActivity.start(activity!!, FilterListActivity.FILTER_SORT_BY)
        }
    }

    private fun initCountries() {
        binding.tvCountries.text = (viewModel.getCountry())

        binding.tvCountries.setOnClickListener {
            FilterListActivity.start(activity!!, FilterListActivity.FILTER_COUNTRIES)
        }

        binding.tvCountries.visibility = viewModel.categoryAndCountryVisibility()
    }

    private fun initLanguages() {
        binding.tvLanguages.text = (viewModel.getLanguage())

        binding.tvLanguages.setOnClickListener {
            FilterListActivity.start(activity!!, FilterListActivity.FILTER_LANGUAGES)
        }
    }

    private fun initCategories() {
        binding.tvCategories.text = (viewModel.getCategory())

        binding.tvCategories.setOnClickListener {
            FilterListActivity.start(activity!!, FilterListActivity.FILTER_CATEGORIES)
        }

        binding.tvCategories.visibility = viewModel.categoryAndCountryVisibility()
    }

    private fun initSource() {
        binding.tvSource.text = (viewModel.getSource())

        binding.tvSource.setOnClickListener {
            FilterListActivity.start(activity!!, FilterListActivity.FILTER_SOURCES)
        }
    }

    private fun listenerSearch() {
        binding.etBox.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (!s.empty()) {
                    goToSearch()
                }
            }
        })
    }

    private fun goToSearch() {
        viewModel.setKeyWord(binding.etBox.text.toString())
        viewModel.setDateFrom(binding.tvFrom.text.toString())
        viewModel.setDateTo(binding.tvTo.text.toString())
        listener?.search(viewModel.getFilter())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onClickSearch() {
        goToSearch()
    }

    interface OnFragmentInteractionListener {
        fun search(filter: Filter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && data != null) {

            viewModel.setResult(requestCode, data)

            if (resources.getBoolean(R.bool.twoPaneMode) || viewModel.starting) {
                goToSearch()
            }

            init()
        }

        viewModel.starting = false
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun initializeComponent() {
        DaggerFilterComponent.builder()
                .filterModule(
                        FilterModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    companion object {

        private var INSTANCE: FilterFragment? = null

        @JvmStatic
        fun instance(): FilterFragment {
            return instance(null, false)
        }

        @JvmStatic
        fun instance(filter: Filter?, starting: Boolean): FilterFragment {
            var bundle = Bundle().apply {
                if (filter != null) {
                    putString(Filter.KEY, Filter.adapter.toJson(filter))
                }
                putBoolean(SplashActivity.KEY, starting)
            }
            if (INSTANCE == null) {
                INSTANCE = FilterFragment().apply {
                    arguments = bundle
                }
            } else {
                INSTANCE!!.arguments = bundle
            }
            return INSTANCE as FilterFragment
        }

    }
}
