package com.piyawat.android_coinranking.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.piyawat.android_coinranking.R
import com.piyawat.android_coinranking.databinding.ListCurrencyItemBinding
import com.piyawat.android_coinranking.databinding.ListCurrencyItemDiffBinding
import com.piyawat.android_coinranking.model.Coin

class CoinsListAdapter :
    RecyclerView.Adapter<BaseViewHolder<*>>(), Filterable {

    private val showItems : MutableList<Coin> = ArrayList()
    private val filterItems : MutableList<Coin> = ArrayList()
    private val tempItems : MutableList<Coin> = ArrayList()

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

    fun updateData(data: List<Coin>, isNew : Boolean) {
        if(isNew) showItems.clear()
        showItems.addAll(data.toMutableList())
        tempItems.clear()
        tempItems.addAll(showItems)
        notifyDataSetChanged()
    }

    fun saveFilterTempData(){
        if(filterItems.size > showItems.size) return
        filterItems.clear()
        filterItems.addAll(showItems)
    }

    override fun getItemCount(): Int {
        return showItems.size
    }

    override fun getItemViewType(position: Int): Int {
        if ((position+1) % 5 == 0)
            return VIEW_DIFF
        return VIEW_NORMAL
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is NormalViewHolder -> holder.listCurrencyItemBinding.coinItem = showItems[position]
            is DiffViewHolder -> holder.listCurrencyItemDiffBinding.coinItem = showItems[position]
        }
    }

    override fun getFilter(): Filter {
        return object:Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val textSearch = constraint.toString().toLowerCase()
                val resultList = ArrayList<Coin>()
                if(textSearch.isEmpty()){
                    resultList.addAll(tempItems)
                } else {
                    for(row in filterItems){
                        if(row.name.contains(textSearch) or row.slug.contains(textSearch)
                        or row.symbol.contains(textSearch) or row.id.toString().contains(textSearch)) {
                            resultList.add(row)
                        }
                    }
                }
                val filterResult = Filter.FilterResults()
                filterResult.values = resultList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                showItems.clear()
                showItems.addAll(results!!.values as List<Coin>)
                notifyDataSetChanged()
            }

        }
    }


}