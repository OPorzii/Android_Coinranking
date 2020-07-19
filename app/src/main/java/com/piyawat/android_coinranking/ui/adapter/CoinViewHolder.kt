package com.piyawat.android_coinranking.ui.adapter

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ListCurrencyItemBinding
import com.piyawat.android_coinranking.model.Coin

class CoinViewHolder(private val binding: ListCurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var coin : Coin? = null

    fun bind(coin : Coin?){
        if(coin==null){
            binding.currencyItemName.text = "Unknow"
            binding.currencyItemDesc.text = "Unknow"
            binding.currencyItemImage.setBackgroundColor(Color.parseColor("#8F8F8F"))
        } else {
            showCoinData(coin)
        }
    }

    private fun showCoinData(coin : Coin) {
            this.coin = coin
            binding.currencyItemName.text = coin.name
            coin.description?.let{
                binding.currencyItemDesc.text = it.replace("<[^>]*>".toRegex(), "")
            }

            GlideToVectorYou.init().with(itemView.context).load(Uri.parse(coin.iconUrl), binding.currencyItemImage)
    }


    companion object {
        fun create(parent: ViewGroup): CoinViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_currency_item, parent, false)
            val binding = ListCurrencyItemBinding.bind(view)
            return CoinViewHolder(binding)
        }
    }
}
