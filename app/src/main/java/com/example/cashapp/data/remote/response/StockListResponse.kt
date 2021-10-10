package com.example.cashapp.data.remote.response

import com.example.cashapp.data.model.Stock
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StockListResponse(
    @Expose
    @SerializedName("stocks")
    val stocks: List<Stock>
)
