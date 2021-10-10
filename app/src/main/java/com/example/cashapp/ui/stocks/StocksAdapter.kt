package com.example.cashapp.ui.stocks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cashapp.data.model.Stock
import com.example.cashapp.databinding.StockItemBinding

class StocksAdapter(
    val stocks: ArrayList<Stock>
) : RecyclerView.Adapter<StockItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun appendData(dataList: List<Stock>) {
        val oldCount = itemCount
        stocks.addAll(dataList)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount in 1 until currentCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockItemViewHolder =
        StockItemViewHolder(
            StockItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = stocks.size
    override fun onBindViewHolder(holder: StockItemViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

}
