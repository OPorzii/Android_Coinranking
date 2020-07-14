package com.piyawat.android_coinranking.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.model.Coin

class CoinsListAdapter : PagingDataAdapter<Coin, ViewHolder>(VIEW_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            VIEW_NORMAL -> CoinViewHolder.create(parent)
            VIEW_DIFF -> CoinDiffViewHolder.create(parent)
            else -> throw IllegalArgumentException("Error view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            when(holder){
                is CoinViewHolder -> (holder as CoinViewHolder).bind(item)
                is CoinDiffViewHolder -> (holder as CoinDiffViewHolder).bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if ((position+1) % 5 == 0)
            return VIEW_DIFF
        return VIEW_NORMAL
    }

    companion object {
        private val VIEW_COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem == newItem
        }

        private const val VIEW_NORMAL = 0
        private const val VIEW_DIFF = 1
    }

}