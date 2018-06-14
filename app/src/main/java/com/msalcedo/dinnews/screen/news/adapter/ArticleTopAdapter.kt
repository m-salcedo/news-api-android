package com.msalcedo.dinnews.screen.news.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.models.Article

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/9/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */

class ArticleTopAdapter(
        private val listener: ArticleAdapter.OnArticleSelected
) : PagedListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.list_item_news_head -> ArticleTopViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleTopViewHolder).bindTo(getItem(position))

        holder.itemView.setOnClickListener({
            getItem(position)?.let { it -> listener.onArticleSelected(it) }
        })

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.list_item_news_head
    }


    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

}