package com.piyawat.android_coinranking.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ActivityCoinsListBinding
import com.piyawat.android_coinranking.ui.adapter.CoinLoadStateAdapter
import com.piyawat.android_coinranking.ui.adapter.CoinsListAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class CoinsListActivity : AppCompatActivity() {

    private val viewModel : CoinsListViewModel by viewModel()
    private lateinit var binding: ActivityCoinsListBinding
    private var adapter = CoinsListAdapter()
    private var fetchJob: Job? = null
    private lateinit var layoutManager : LinearLayoutManager
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinsListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()
        setupAdapter()
        setupListener()
//        setupObserver()

        // fetch first data set
        fetchCoinData(null)



        binding.retryButton.setOnClickListener{ adapter.retry() }

    }

    private fun setupAdapter() {
        binding.coinsListView.adapter = adapter.withLoadStateFooter(CoinLoadStateAdapter { adapter.retry() })
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.coinsListView.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.swipeLayout.isRefreshing = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun fetchCoinData(query : String?) {
        fetchJob?.cancel()
        fetchJob = lifecycleScope.launch {
            viewModel.fetchCoinsList(query)?.collectLatest {
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
    }

    private fun setupListener(){

        binding.swipeLayout.setOnRefreshListener {
            searchCoinList()
        }

        binding.searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchCoinList()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                handler.apply {
                    removeCallbacksAndMessages(null)
                }.postDelayed({
                    searchCoinList()
                }, 1000)
                return false
            }
        })


    }

    private fun searchCoinList() {
        val queryText = binding.searchInput.query.toString().trim()
        fetchCoinData(if(queryText.isEmpty()) null else queryText )
        Log.d("TEST_TYPING", binding.searchInput.query.toString())

//        Toast.makeText(this, binding.searchInput.query.toString(), Toast.LENGTH_SHORT).show()
    }

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
            binding.coinsListView.scrollToPosition(0)
        }

    }


}