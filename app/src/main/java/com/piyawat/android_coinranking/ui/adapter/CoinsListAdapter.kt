package com.piyawat.android_coinranking.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.model.Coin

class CoinsListAdapter : PagingDataAdapter<Coin, ViewHolder>(REPO_COMPARATOR) {

    enum class CoinItemView {
        VIEW_NORMAL, VIEW_DIFF
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CoinViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as CoinViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if ((position+1) % 5 == 0)
            return R.layout.list_currency_item
        return R.layout.list_currency_item_diff
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