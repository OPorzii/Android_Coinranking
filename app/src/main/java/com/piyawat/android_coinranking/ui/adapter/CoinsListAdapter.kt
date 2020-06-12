package com.piyawat.android_coinranking.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ListCurrencyItemBinding
import com.piyawat.android_coinranking.databinding.ListCurrencyItemDiffBinding
import com.piyawat.android_coinranking.model.Coin

class CoinsListAdapter(private val listCoins: List<Coin>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        private const val VIEW_DIFF = 0
        private const val VIEW_NORMAL = 1
    }


    inner class NormalViewHolder(val listCurrencyItemBinding : ListCurrencyItemBinding) :
        BaseViewHolder<ListCurrencyItemBinding>(listCurrencyItemBinding.root)

    inner class DiffViewHolder(val listCurrencyItemDiffBinding: ListCurrencyItemDiffBinding) :
        BaseViewHolder<ListCurrencyItemDiffBinding>(listCurrencyItemDiffBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            VIEW_NORMAL -> {
                NormalViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_currency_item,
                    parent,
                    false
                ))
            }
            VIEW_DIFF -> {
                DiffViewHolder(DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_currency_item_diff,
                    parent,
                    false
                ))
            }
            else -> throw IllegalArgumentException("Error view type")
        }
    }

    override fun getItemCount(): Int {
        return listCoins.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 5 == 0)
            return VIEW_DIFF
        return VIEW_NORMAL
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is NormalViewHolder -> holder.listCurrencyItemBinding.coinItem = listCoins[position]
            is DiffViewHolder -> holder.listCurrencyItemDiffBinding.coinItem = listCoins[position]
        }
    }


}