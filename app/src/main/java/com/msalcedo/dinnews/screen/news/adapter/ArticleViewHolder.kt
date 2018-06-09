package com.msalcedo.dinnews.screen.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.models.Article

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(article: Article?) {
//        itemView.UserName.text = user?.login
//        GlideApp.with(itemView.context)
//                .load(user?.avatarUrl)
//                .placeholder(R.mipmap.ic_launcher)
//                .into(itemView.UserAvatar)
//        itemView.siteAdminIcon.visibility = if (user!!.siteAdmin) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): ArticleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_news, parent, false)
            return ArticleViewHolder(view)
        }
    }

}