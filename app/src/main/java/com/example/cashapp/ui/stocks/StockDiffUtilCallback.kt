package com.example.cashapp.ui.stocks

import androidx.recyclerview.widget.DiffUtil
import com.example.cashapp.data.model.Stock

class StockDiffUtilCallback: DiffUtil.ItemCallback<Stock>() {
    override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
        return oldItem.ticker == newItem.ticker
    }

    override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
        return oldItem == newItem
    }
}
