package com.msalcedo.dinnews.screen.news.adapter

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.models.Article

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ArticleAdapter(
        private val retryCallback: () -> Unit,
        private val listener: OnArticleSelected
) : PagedListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback) {

    private var articleAdapter: ArticleTopAdapter = ArticleTopAdapter(listener)
    private var listTop: PagedList<Article>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        initAdapter()
        return when (viewType) {
            R.layout.list_item_news -> ArticleViewHolder.create(parent)
            R.layout.list_item_title -> TitleViewHolder.create(parent)
            R.layout.item_list_header -> RecyclerBarViewHolder.create(parent, articleAdapter!!)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.list_item_news -> {
                (holder as ArticleViewHolder).bindTo(getItem(getPosition(position)))
                holder.itemView.setOnClickListener({
                    getItem(getPosition(position))?.let { it -> listener.onArticleSelected(it) }
                })
            }

            R.layout.item_list_header -> (holder as RecyclerBarViewHolder).bindTo()

            R.layout.list_item_title -> (holder as TitleViewHolder)
                    .bindTo(
                            if (position == 0) R.string.trending_topic
                            else R.string.more_news
                    )
        }
    }

    private fun getPosition(position: Int): Int {
        return if (hasExtraRow()) position - 3 else position
    }

    private fun hasExtraRow(): Boolean {
        return articleAdapter.itemCount != 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && hasExtraRow()) {
            R.layout.list_item_title
        } else if (position == 1 && hasExtraRow()) {
            R.layout.item_list_header
        } else if (position == 2 && hasExtraRow()) {
            R.layout.list_item_title
        } else {
            R.layout.list_item_news
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 3 else 0
    }

    fun listTopHeadlines(list: PagedList<Article>?) {
        listTop = list
        articleAdapter.submitList(list)
        notifyDataSetChanged()
    }

    private fun initAdapter() {
        if (listTop != null) {
            articleAdapter.submitList(listTop)
        }
    }

    interface OnArticleSelected {
        fun onArticleSelected(article: Article);
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