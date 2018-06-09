package com.msalcedo.dinnews.screen.news.adapter

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.AdapterView
import com.msalcedo.dinnews.R
import com.msalcedo.dinnews.models.Article
import com.msalcedo.dinnews.screen.news.datasource.NetworkState

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 6/8/18.
 * Copyright (c) m-salcedo. All rights reserved.
 */
class ArticleAdapter(
        private val retryCallback: () -> Unit,
        private val onItemClickListener: AdapterView.OnItemClickListener
) : PagedListAdapter<Article, RecyclerView.ViewHolder>(DiffCallback) {

    private var networkState: NetworkState? = null
    private var articleAdapter: ArticleTopAdapter? = null
    private var listTop: PagedList<Article>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        initAdapter()
        return when (viewType) {
            R.layout.list_item_news -> ArticleViewHolder.create(parent)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent, retryCallback)
            R.layout.item_list_header -> RecyclerBarViewHolder.create(parent, articleAdapter!!)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.list_item_news -> {
                (holder as ArticleViewHolder).bindTo(getItem(position - 1))
                holder.itemView.setOnClickListener({
                    onItemClickListener.onItemClick(null, holder.itemView, position, position.toLong())
                })
            }
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bindTo(networkState)
            R.layout.item_list_header -> (holder as RecyclerBarViewHolder).bindTo()
        }
    }

    private fun hasExtraRow(): Boolean {
        return false /*networkState != null && networkState != NetworkState.LOADED*/
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else if (position == 0) {
            R.layout.item_list_header
        } else R.layout.list_item_news
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0 + 1
    }

    /**
     * Set the current network state to the adapter
     * but this work only after the initial load
     * and the adapter already have list to add new loading raw to it
     * so the initial loading state the activity responsible for handle it
     *
     * @param newNetworkState the new network state
     */
    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    fun setInitialLoadingState(networkState: NetworkState?) {
        this.networkState = networkState
    }

    fun listTopHeadlines(list: PagedList<Article>?) {
        listTop = list
        articleAdapter?.submitList(list)
        notifyDataSetChanged()
    }

    private fun initAdapter() {
        articleAdapter = ArticleTopAdapter(onItemClickListener)
        if (listTop != null) {
            articleAdapter!!.submitList(listTop)
        }
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