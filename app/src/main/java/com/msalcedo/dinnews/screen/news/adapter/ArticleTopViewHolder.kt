package com.msalcedo.dinnews.screen.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.app.Application
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.utils.RoundedCornersTransform
import kotlinx.android.synthetic.main.list_item_news_head.view.*

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ArticleTopViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(article: Article?) {
        itemView.tvNewsTitle.text = article!!.title
        Application.component.picasso().load(article.urlToImage)
                .placeholder(R.drawable.placeholder_bg)
                .transform(RoundedCornersTransform())
                .into(itemView.ivNews)
    }

    companion object {
        fun create(parent: ViewGroup): ArticleTopViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_news_head, parent, false)
            return ArticleTopViewHolder(view)
        }
    }

}