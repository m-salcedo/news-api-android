package com.msalcedo.dinnews.screen.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.screen.news.datasource.NetworkState
import com.msalcedo.dinnews.screen.news.datasource.Status
import kotlinx.android.synthetic.main.item_network_state.view.*

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class NetworkStateViewHolder(val view: View, private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        itemView.retryLoadingButton.setOnClickListener { retryCallback() }
    }

    fun bindTo(networkState: NetworkState?) {
        //error message
        if (networkState != null) {
            itemView.errorMessageTextView.visibility = if (networkState.message != null) View.VISIBLE else View.GONE
        }
        if (networkState != null) {
            if (networkState.message != null) {
                itemView.errorMessageTextView.text = networkState.message
            }
        }

        //loading and retry
        if (networkState != null) {
            itemView.retryLoadingButton.visibility = if (networkState.status == Status.FAILED) View.VISIBLE else View.GONE
        }
        if (networkState != null) {
            itemView.loadingProgressBar.visibility = if (networkState.status == Status.RUNNING) View.VISIBLE else View.GONE
        }
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }
    }

}