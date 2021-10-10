package com.example.cashapp.di.modules

import android.app.Application
import com.example.cashapp.BuildConfig
import com.example.cashapp.CashApplication
import com.example.cashapp.data.remote.NetworkService
import com.example.cashapp.data.remote.Networking
import com.example.cashapp.utils.network.NetworkHelper
import com.example.cashapp.utils.rx.RxSchedulerProvider
import com.example.cashapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: CashApplication) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesNetworkService(): NetworkService = Networking
        .create(
            baseUrl = BuildConfig.BASE_URL,
            cacheDir = application.cacheDir,
            cacheSize = 10485760L
        )

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun providesSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

}
