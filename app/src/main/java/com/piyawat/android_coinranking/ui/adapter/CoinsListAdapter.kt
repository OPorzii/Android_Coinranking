package com.piyawat.android_coinranking.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.piyawat.android_coinranking.model.Coin

class CoinsListAdapter : PagingDataAdapter<Coin, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return CoinViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as CoinViewHolder).bind(item)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem == newItem
        }
    }

}