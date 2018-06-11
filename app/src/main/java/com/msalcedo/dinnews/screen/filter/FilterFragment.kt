package com.msalcedo.dinnews.screen.filter

import android.app.DatePickerDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxFragment
import com.msalcedo.dinnews.common.ext.empty
import com.msalcedo.dinnews.databinding.FragmentFilterBinding
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.screen.filter.di.DaggerFilterComponent
import com.msalcedo.dinnews.screen.filter.di.FilterModule
import com.msalcedo.dinnews.screen.filter.mvvm.FilterViewModel
import com.msalcedo.dinnews.screen.news.events.NewListEvent
import org.joda.time.DateTime
import timber.log.Timber
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

    private var checkSpLanguage: Boolean = false
    private var checkSpCountry: Boolean = false
    private var checkSpCategory: Boolean = false
    private var checkSpSort: Boolean = false


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

        return this.binding.root
    }

    override fun init() {
        initBoxSearch()
        initPicketDate()
        initCategories()
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

    fun fromDatePickerShow() {
        val currentYear: Int
        val currentMonth: Int
        val currentDay: Int

        var time: DateTime = DateTime.now()

        if (!viewModel.getFromDate().empty()) {
            time = viewModel.parserDate().parseDateTime(viewModel.getFromDate())
            Timber.d(Gson().toJson(time))
        }

        currentYear = time.year
        currentMonth = time.monthOfYear - 1
        currentDay = time.dayOfMonth

        DatePickerDialog(context, fromListener, currentYear, currentMonth, currentDay).show()
    }

    fun toDatePickerShow() {
        val currentYear: Int
        val currentMonth: Int
        val currentDay: Int

        var time: DateTime = DateTime.now()

        if (!viewModel.getToDate().empty()) {
            time = viewModel.parserDate().parseDateTime(viewModel.getToDate())
            Timber.d(Gson().toJson(time))
        }

        currentYear = time.year
        currentMonth = time.monthOfYear - 1
        currentDay = time.dayOfMonth

        DatePickerDialog(context, toListener, currentYear, currentMonth, currentDay).show()
    }

    val fromListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

        var monthStr = month
        monthStr++

        binding.tvFrom.text = ("$year-$monthStr-$dayOfMonth")
        Timber.d(binding.tvFrom.text.toString())
        viewModel.setDateFrom(binding.tvFrom.text.toString())
        if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
    }

    val toListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        var monthStr = month
        monthStr++

        binding.tvTo.text = ("$year-$monthStr-$dayOfMonth")
        Timber.d(binding.tvTo.text.toString())
        viewModel.setDateTo(binding.tvTo.text.toString())
        if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
    }

    override fun initLandscape() {
        super.initLandscape()
        binding.btn.visibility = View.GONE

        listenerSearch()
    }

    private fun initSort() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.sortBy, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSortBy.adapter = adapter

        binding.spSortBy.setSelection(viewModel.getFilter().positionSortBy)

        binding.spSortBy.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                if (checkSpSort) {
                    val array = resources.getStringArray(R.array.sortBy_code)
                    viewModel.setSortBy(array[position], position)
                    if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
                }
                checkSpSort = true
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }
    }

    private fun initCountries() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.countries, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCountries.adapter = adapter

        binding.spCountries.setSelection(viewModel.getFilter().positionCountry)

        binding.spCountries.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                if (checkSpCountry) {
                    if (position - 1 < 0) {
                        viewModel.setCountry("", position)
                    } else {
                        val array = resources.getStringArray(R.array.countries_code)
                        viewModel.setCountry(array[position - 1], position)
                    }
                    if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
                }
                checkSpCountry = true
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }
    }

    private fun initLanguages() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.languages, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spLanguages.adapter = adapter

        binding.spLanguages.setSelection(viewModel.getFilter().positionLanguage)

        binding.spLanguages.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                if (checkSpLanguage) {
                    if (position - 1 < 0) {
                        viewModel.setLanguage("", position)
                    } else {
                        val array = resources.getStringArray(R.array.languages_code)
                        viewModel.setLanguage(array[position - 1], position)
                    }
                    if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
                }
                checkSpLanguage = true
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }
    }

    private fun initCategories() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.categories, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategories.adapter = adapter

        binding.spCategories.setSelection(viewModel.getFilter().positionCategory)

        binding.spCategories.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                if (checkSpCategory) {
                    if (position == 0) {
                        viewModel.setCategory("", position)
                    } else {
                        val array = resources.getStringArray(R.array.categories)
                        viewModel.setCategory(array[position], position)
                    }

                    if (resources.getBoolean(R.bool.twoPaneMode)) goToSearch()
                }
                checkSpCategory = true
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

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

    override fun initializeComponent() {
        DaggerFilterComponent.builder()
                .filterModule(
                        FilterModule(this))
                .appComponent(Application.component)
                .build()
                .inject(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(filter: Filter) =
                FilterFragment().apply {
                    arguments = Bundle().apply {
                        putString(Filter.KEY, Filter.adapter.toJson(filter))
                    }
                }
    }
}
