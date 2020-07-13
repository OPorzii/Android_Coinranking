package com.piyawat.android_coinranking.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ListCurrencyItemBinding
import com.piyawat.android_coinranking.databinding.ListCurrencyItemDiffBinding
import com.piyawat.android_coinranking.model.Coin

class CoinDiffViewHolder(private val binding: ListCurrencyItemDiffBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(coin : Coin){
        binding.currencyItemName.text = coin.name
        GlideToVectorYou.init().with(itemView.context).load(Uri.parse(coin.iconUrl), binding.currencyItemImage)
    }

    companion object {
        fun create(parent: ViewGroup): CoinViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_currency_item_diff, parent, false)
            val binding = ListCurrencyItemBinding.bind(view)
            return CoinViewHolder(binding)
        }
    }
}