package com.example.cashapp.data.remote

import com.example.cashapp.data.remote.response.StockListResponse
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET(Endpoints.STOCKS_LIST)
    fun getStockListCall(): Single<StockListResponse>
}
