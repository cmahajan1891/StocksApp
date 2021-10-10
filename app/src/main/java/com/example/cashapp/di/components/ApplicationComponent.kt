package com.example.cashapp.di.components

import android.app.Application
import com.example.cashapp.CashApplication
import com.example.cashapp.data.remote.NetworkService
import com.example.cashapp.di.modules.ApplicationModule
import com.example.cashapp.utils.network.NetworkHelper
import com.example.cashapp.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: CashApplication)
    fun getApplication(): Application
    fun getNetworkService(): NetworkService
    fun getCompositeDisposable(): CompositeDisposable
    fun getSchedulerProvider(): SchedulerProvider
    fun getNetworkHelper(): NetworkHelper
}
