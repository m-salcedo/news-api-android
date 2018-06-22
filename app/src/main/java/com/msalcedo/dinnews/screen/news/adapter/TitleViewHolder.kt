package com.msalcedo.dinnews.screen.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import kotlinx.android.synthetic.main.list_item_title.view.*

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class TitleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(string: Int) {
        itemView.tvTitle.text = Application.component.resources().getString(string)
    }

    companion object {
        fun create(parent: ViewGroup): TitleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_title, parent, false)
            return TitleViewHolder(view)
        }
    }

}