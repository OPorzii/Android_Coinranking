package com.piyawat.android_coinranking.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.LoadStateFooterBinding

class CoinLoadStateViewHolder(private val binding: LoadStateFooterBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {


    init {
        binding.retryButton.setOnClickListener {
            Log.d("ADAPTER_RELOAD", "RETRY CLICKED")
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CoinLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_footer, parent, false)
            val binding = LoadStateFooterBinding.bind(view)
            return CoinLoadStateViewHolder(binding, retry)
        }
    }

}