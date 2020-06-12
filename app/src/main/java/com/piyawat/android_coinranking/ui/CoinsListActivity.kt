package com.piyawat.android_coinranking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ActivityCoinsListBinding
import com.piyawat.android_coinranking.ui.adapter.CoinsListAdapter
import kotlinx.android.synthetic.main.activity_coins_list.*

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

        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        coins_list_view.addItemDecoration(itemDecorator)
        coins_list_view.layoutManager = LinearLayoutManager(this)
        coins_list_view.setHasFixedSize(true)
        coins_list_view.adapter = adapter



        viewModel.getCoins(0, 10)

        viewModel.coinsList.observe(this, Observer {
            if(it == null) return@Observer

            adapter.updateData(it, false)

        })


    }



}