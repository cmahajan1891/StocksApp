package com.example.cashapp.ui.stocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cashapp.data.model.Stock
import com.example.cashapp.databinding.StockItemBinding

class StocksAdapter : ListAdapter<Stock, StockItemViewHolder>(
    StockDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockItemViewHolder =
        StockItemViewHolder(
            StockItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: StockItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
