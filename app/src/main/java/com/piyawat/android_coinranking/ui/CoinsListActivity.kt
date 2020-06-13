package com.piyawat.android_coinranking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ActivityCoinsListBinding
import com.piyawat.android_coinranking.ui.adapter.CoinsListAdapter
import kotlinx.android.synthetic.main.activity_coins_list.*

class CoinsListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinsListViewModel

    private var adapter = CoinsListAdapter()
    private lateinit var layoutManager : LinearLayoutManager
    private var isLoadMore = false
    private var page = 1
    private val limit = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCoinsListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_coins_list)
        viewModel = ViewModelProvider(this).get(CoinsListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupUI()
        setupListener()
        setupObserver()

        viewModel.fetchCoinsList(0, limit)




    }

    private fun setupUI(){
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        coins_list_view.addItemDecoration(itemDecorator)
        layoutManager = LinearLayoutManager(this)
        coins_list_view.layoutManager = layoutManager
        coins_list_view.setHasFixedSize(true)
        coins_list_view.adapter = adapter
    }

    private fun setupListener(){
        swipe_layout.setOnRefreshListener {
            page = 1
            viewModel.fetchCoinsList(0, limit)
        }

        search_input.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length == 1) adapter.saveFilterTempData()
                adapter.filter.filter(newText)
                return false
            }

        })

        coins_list_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && search_input.query.isEmpty()) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    if (!isLoadMore) {
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            isLoadMore = true
                            val nextPage = ((page++) * limit)
                            viewModel.fetchCoinsList(nextPage, limit)
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            }
        })
    }

    private fun setupObserver() {
        viewModel.coinsList.observe(this, Observer {
            if(it == null) return@Observer
            adapter.updateData(it, !isLoadMore)
            isLoadMore = false
        })

        viewModel.isLoading.observe(this, Observer {
            if(it == null) return@Observer
            swipe_layout.isRefreshing = it
        })
    }



}