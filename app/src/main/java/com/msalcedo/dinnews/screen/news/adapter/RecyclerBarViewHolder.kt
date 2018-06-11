package com.msalcedo.dinnews.screen.news.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R

class RecyclerBarViewHolder(
        val context: Context, val view: View, adapter: ArticleTopAdapter
) : RecyclerView.ViewHolder(view) {

    init {
        val linearLayoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false)
        (view.findViewById<RecyclerView>(R.id.recycler)).layoutManager = linearLayoutManager
        (view.findViewById<RecyclerView>(R.id.recycler)).adapter = adapter
    }

    fun bindTo() {}

    companion object {
        fun create(parent: ViewGroup, articleAdapter: ArticleTopAdapter): RecyclerBarViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_list_header,
                    parent,
                    false)
            return RecyclerBarViewHolder(parent.context, view, articleAdapter)
        }
    }
}

