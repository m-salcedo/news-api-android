package com.msalcedo.dinnews.screen.filterlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.common.ext.empty
import com.msalcedo.dinnews.models.Source
import kotlinx.android.synthetic.main.item_list_filter.view.*

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/12/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class FilterAdapter(private var sources: MutableList<Source>,
                    private val listener: OnItemClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolderItem(v: View) : RecyclerView.ViewHolder(v) {

        fun bindTo(item: Source) {
            itemView.tvTitle.text = item.name
            itemView.tvDescription.text = item.description
            itemView.tvUrl.text = item.url

            if (item.description.empty()) {
                itemView.tvDescription.visibility = View.GONE
            } else {
                itemView.tvDescription.visibility = View.VISIBLE
            }

            if (item.url.empty()) {
                itemView.tvUrl.visibility = View.GONE
            } else {
                itemView.tvUrl.visibility = View.VISIBLE
            }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolderItem {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_list_filter,
                        parent, false)
                return ViewHolderItem(view)
            }
        }
    }

    init {
        this.sources = (sources.sortedWith(compareBy({ it.name }))).toMutableList()
        if (sources.size > 3) {
            sources.add(0, Source(Application.component.resources()
                    .getString(R.string.hint_filter), ""))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderItem.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItem).bindTo(getItem(position))
        holder.itemView.setOnClickListener({
            getItem(position).let { it -> listener.onClickItem(holder.itemView, it) }
        })
    }

    private fun getItem(pos: Int): Source {
        return sources[pos]
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    interface OnItemClickListener {
        fun onClickItem(view: View, sourceFilterableAA: Source)
    }

    companion object {
        private val TAG = FilterAdapter::class.java.simpleName
    }
}