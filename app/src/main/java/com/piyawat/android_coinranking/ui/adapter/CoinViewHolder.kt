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
import com.piyawat.android_coinranking.model.Coin

class CoinViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.currency_item_name)
    private val description: TextView = view.findViewById(R.id.currency_item_desc)
    private val coinImage: ImageView = view.findViewById(R.id.currency_item_image)

    private var coin : Coin? = null

    fun bind(coin : Coin?){
        if(coin==null){
            name.text = "Unknow"
            description.text = "Unknow"
            coinImage.setBackgroundColor(Color.parseColor("#8F8F8F"))
        } else {
            showCoinData(coin)
        }
    }

    private fun showCoinData(coin : Coin) {
            this.coin = coin
            name.text = coin.name
            description.text = coin.description
            GlideToVectorYou.init().with(itemView.context).load(Uri.parse(coin.iconUrl), coinImage)
    }


    companion object {
        fun create(parent: ViewGroup): CoinViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_currency_item, parent, false)
            return CoinViewHolder(view)
        }
    }
}
