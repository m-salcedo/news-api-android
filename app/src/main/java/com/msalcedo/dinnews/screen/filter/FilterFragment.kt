package com.msalcedo.dinnews.screen.filter

import android.app.DatePickerDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.RxFragment
import com.msalcedo.dinnews.databinding.FragmentFilterBinding
import com.msalcedo.dinnews.models.Filter
import com.msalcedo.dinnews.screen.filter.di.DaggerFilterComponent
import com.msalcedo.dinnews.screen.filter.di.FilterModule
import com.msalcedo.dinnews.screen.filter.mvvm.FilterViewModel
import com.msalcedo.dinnews.screen.news.events.NewListEvent
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class FilterFragment : RxFragment(), NewListEvent {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var binding: FragmentFilterBinding
    private val filter: Filter = Filter()
    private lateinit var date: DatePickerDialog.OnDateSetListener

    @Inject
    lateinit var viewModel: FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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
        Timber.d("test " + context?.getString(R.string.api_key_news))

        initPicketDate()
        initCategories()
        initLanguages()
        initCountries()
        initSort()
    }


    private fun initPicketDate() {
        binding.tvFrom.setOnClickListener {
            DatePickerDialog(
                    context, null, 2018, 3, 23).show()
        }
        binding.tvTo.setOnClickListener {
            DatePickerDialog(
                    context, null, 2018, 3, 23).show()
        }
    }

    override fun initLandscape() {
        super.initLandscape()
        binding.btn.visibility = View.GONE
    }

    private fun initSort() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.sortBy, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSortBy.adapter = adapter
    }

    private fun initCountries() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.countries, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCountries.adapter = adapter
    }

    private fun initLanguages() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.languages, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spLanguages.adapter = adapter
    }

    private fun initCategories() {
        val adapter = ArrayAdapter.createFromResource(
                context, R.array.categories, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategories.adapter = adapter
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
        listener?.onSearch(filter)
    }

    interface OnFragmentInteractionListener {
        fun onSearch(filter: Filter)
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
        fun newInstance() =
                FilterFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
