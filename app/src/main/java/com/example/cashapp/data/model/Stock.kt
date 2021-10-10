package com.example.cashapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Stock(
    @Expose
    @SerializedName("ticker")
    val ticker: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("currency")
    val currency: String,

    @Expose
    @SerializedName("current_price_cents")
    val currentPriceCents: Int,

    @Expose
    @SerializedName("quantity")
    val quantity: Int? = null,

    @Expose
    @SerializedName("current_price_timestamp")
    val currentPriceTimeStamp: Int

)
