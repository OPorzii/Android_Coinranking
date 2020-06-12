package com.piyawat.android_coinranking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ActivityCoinsListBinding
import com.piyawat.android_coinranking.ui.adapter.CoinsListAdapter

class CoinsListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinsListViewModel
    private var adapter = CoinsListAdapter(listOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCoinsListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_coins_list)
        viewModel = ViewModelProvider(this).get(CoinsListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


    }
}