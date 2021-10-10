package com.example.cashapp.data.repository

import com.example.cashapp.data.model.Stock
import com.example.cashapp.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

class StocksRepository @Inject constructor(
    private val networkService: NetworkService
) {
    fun fetchStocks(): Single<List<Stock>> = networkService.getStockListCall().map { it.stocks }
}
