package com.piyawat.android_coinranking.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ActivityCoinsListBinding
import com.piyawat.android_coinranking.ui.adapter.CoinsListAdapter
import kotlinx.android.synthetic.main.activity_coins_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CoinsListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinsListViewModel
    private lateinit var binding: ActivityCoinsListBinding
    private var adapter = CoinsListAdapter()
    private var fetchJob: Job? = null


    private lateinit var layoutManager : LinearLayoutManager
    private var isLoadMore = false
    private var page = 1
    private val limit = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinsListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // get the view model
        viewModel = ViewModelProvider(this).get(CoinsListViewModel::class.java)

        setupUI()
//        setupListener()
//        setupObserver()

        // fetch first data set
        fetchCoinData()

    }

    private fun fetchCoinData() {
        fetchJob?.cancel()
        fetchJob = lifecycleScope.launch {
            viewModel.fetchCoinsList()?.collectLatest {
                adapter.submitData(it)
            }
        }
    }


    private fun setupUI(){
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        layoutManager = LinearLayoutManager(this)
        binding.coinsListView.addItemDecoration(itemDecorator)
        binding.coinsListView.layoutManager = layoutManager
        binding.coinsListView.setHasFixedSize(true)
        binding.coinsListView.adapter = adapter
    }

//    private fun setupListener(){


//        search_input.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if(newText?.length == 1) adapter.saveFilterTempData()
//                adapter.filter.filter(newText)
//                return false
//            }
//
//        })

//        coins_list_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0 && search_input.query.isEmpty()) {
//                    val visibleItemCount = layoutManager.childCount
//                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
//                    val total = adapter.itemCount
//                    if (!isLoadMore) {
//                        if ((visibleItemCount + pastVisibleItem) >= total) {
//                            isLoadMore = true
//                            val nextPage = ((page++) * limit)
//                            viewModel.fetchCoinsList(nextPage, limit)
//                        }
//                    }
//                    super.onScrolled(recyclerView, dx, dy)
//                }
//            }
//        })
//    }

//    private fun setupObserver() {
//        viewModel.coinsList.observe(this, Observer {
//            if(it == null) return@Observer
//            adapter.updateData(it, !isLoadMore)
//            isLoadMore = false
//        })
//
//        viewModel.isLoading.observe(this, Observer {
//            if(it == null) return@Observer
//            swipe_layout.isRefreshing = it
//        })
//    }

    override fun onBackPressed() {
        if(layoutManager.findFirstCompletelyVisibleItemPosition()==0){
            super.onBackPressed()
        } else {
            binding.coinsListView.smoothScrollToPosition(0)
        }

    }


}