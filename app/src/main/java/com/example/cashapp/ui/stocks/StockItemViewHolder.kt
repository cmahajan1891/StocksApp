package com.example.cashapp.ui.stocks

import androidx.recyclerview.widget.RecyclerView
import com.example.cashapp.data.model.Stock
import com.example.cashapp.databinding.StockItemBinding
import java.text.NumberFormat
import java.util.*

class StockItemViewHolder(private val binding: StockItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(stock: Stock) {
        binding.price.text = formatPrice(stock.currentPriceCents, stock.currency)
        binding.ticker.text = stock.ticker
    }

    private fun formatPrice(price: Int, currency: String): String {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance(currency)
        return formatter.format(price.toDouble() / 100)
    }

}
