package com.example.cashapp.ui.stocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cashapp.R
import com.example.cashapp.data.model.Stock
import com.example.cashapp.data.repository.StocksRepository
import com.example.cashapp.utils.common.Resource
import com.example.cashapp.utils.common.Status
import com.example.cashapp.utils.network.NetworkHelper
import com.example.cashapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

class StockListFragmentViewModel(
    schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper,
    stocksRepository: StocksRepository
) : ViewModel() {

    val stocks: MutableLiveData<Resource<List<Stock>>> = MutableLiveData()

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    val emptyState: LiveData<Boolean> = Transformations.map(stocks) { it.status == Status.EMPTY }
    val errorState: LiveData<Boolean> = Transformations.map(stocks) { it.status == Status.ERROR }
    val loadingState: LiveData<Boolean> =
        Transformations.map(stocks) { it.status == Status.LOADING }
    val successState: LiveData<Boolean> =
        Transformations.map(stocks) { it.status == Status.SUCCESS }

    init {
        if (stocks.value == null && checkInternetConnectionWithMessage()) {
            stocks.postValue(Resource.loading())
            compositeDisposable.add(
                stocksRepository
                    .fetchStocks()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            if (it.isNotEmpty()) {
                                stocks.postValue(Resource.success(it))
                            } else {
                                stocks.postValue(Resource.empty(it))
                            }
                        },
                        {
                            stocks.postValue(Resource.error())
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }

    private fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    private fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.network_default_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(Resource.error(R.string.permission_denied))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}
