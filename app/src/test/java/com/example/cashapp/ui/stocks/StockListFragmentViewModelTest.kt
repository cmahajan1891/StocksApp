package com.example.cashapp.ui.stocks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cashapp.R
import com.example.cashapp.data.model.Stock
import com.example.cashapp.data.repository.StocksRepository
import com.example.cashapp.rx.TestSchedulerProvider
import com.example.cashapp.utils.common.Resource
import com.example.cashapp.utils.network.NetworkHelper
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StockListFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var stocksRepository: StocksRepository

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var messageStringIdObserver: Observer<Resource<Int>>

    @Mock
    private lateinit var stocksObserver: Observer<Resource<List<Stock>>>

    @Test
    fun givenServerResponse200_whenCallFetchStocks_shouldShowStocksInfo() {
        val stock1 = Stock(
            ticker = "TWTR",
            name = "Twitter, Inc.",
            currency = "USD",
            currentPriceCents = 3833,
            quantity = 1,
            currentPriceTimeStamp = 1597942580
        )
        val stock2 = Stock(
            ticker = "^GSPC",
            name = "S&P 500",
            currency = "USD",
            currentPriceCents = 318157,
            currentPriceTimeStamp = 1597942580
        )
        val stocks = listOf(stock1, stock2)
        Mockito.doReturn(true).`when`(networkHelper).isNetworkConnected()
        Mockito.doReturn(Single.just(stocks)).`when`(stocksRepository).fetchStocks()
        val compositeDisposable = CompositeDisposable()
        val testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        val stockListFragmentViewModel = StockListFragmentViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            stocksRepository
        )
        stockListFragmentViewModel.stocks.observeForever(stocksObserver)
        verify(stocksObserver).onChanged(Resource.loading())
        testScheduler.triggerActions()
        verify(stocksRepository).fetchStocks()
        assert(stockListFragmentViewModel.stocks.value == Resource.success(stocks))
        verify(stocksObserver).onChanged(Resource.success(stocks))
        stockListFragmentViewModel.stocks.removeObserver(stocksObserver)
    }

    @Test
    fun givenServerResponse200_whenCallFetchStocks_shouldShowEmptyState() {
        Mockito.doReturn(true).`when`(networkHelper).isNetworkConnected()
        Mockito.doReturn(Single.just(emptyList<Stock>())).`when`(stocksRepository).fetchStocks()
        val compositeDisposable = CompositeDisposable()
        val testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        val stockListFragmentViewModel = StockListFragmentViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            stocksRepository
        )
        stockListFragmentViewModel.stocks.observeForever(stocksObserver)
        verify(stocksObserver).onChanged(Resource.loading())
        testScheduler.triggerActions()
        verify(stocksRepository).fetchStocks()
        assert(stockListFragmentViewModel.stocks.value == Resource.empty(emptyList<Stock>()))
        verify(stocksObserver).onChanged(Resource.empty(emptyList()))
        stockListFragmentViewModel.stocks.removeObserver(stocksObserver)
    }


    @Test
    fun givenNoInternet_whenAppLaunched_shouldShowNetworkError() {
        val testScheduler = TestScheduler()
        val compositeDisposable = CompositeDisposable()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        val stockListFragmentViewModel = StockListFragmentViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            stocksRepository
        )
        stockListFragmentViewModel.messageStringId.observeForever(messageStringIdObserver)
        assert(stockListFragmentViewModel.messageStringId.value == Resource.error(R.string.network_connection_error))
        verify(messageStringIdObserver).onChanged(Resource.error(R.string.network_connection_error))
        stockListFragmentViewModel.messageStringId.removeObserver(messageStringIdObserver)
    }

}
