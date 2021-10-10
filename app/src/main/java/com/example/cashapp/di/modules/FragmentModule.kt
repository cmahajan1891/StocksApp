package com.example.cashapp.di.modules

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cashapp.data.repository.StocksRepository
import com.example.cashapp.ui.stocks.StockListFragment
import com.example.cashapp.ui.stocks.StockListFragmentViewModel
import com.example.cashapp.ui.stocks.StocksAdapter
import com.example.cashapp.utils.ViewModelProviderFactory
import com.example.cashapp.utils.network.NetworkHelper
import com.example.cashapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: StockListFragment) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideStockListViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        stocksRepository: StocksRepository
    ): StockListFragmentViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(StockListFragmentViewModel::class) {
                StockListFragmentViewModel(
                    schedulerProvider,
                    compositeDisposable,
                    networkHelper,
                    stocksRepository
                )
            }
        ).get(StockListFragmentViewModel::class.java)


    @Provides
    fun provideStocksAdapter() = StocksAdapter(ArrayList())

}
