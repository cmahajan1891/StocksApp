package com.example.cashapp.di.components

import com.example.cashapp.di.modules.FragmentModule
import com.example.cashapp.di.scopes.FragmentScope
import com.example.cashapp.ui.stocks.StockListFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: StockListFragment)
}
