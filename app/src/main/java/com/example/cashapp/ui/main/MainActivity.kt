package com.example.cashapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cashapp.R
import com.example.cashapp.ui.stocks.StockListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        addStocksListFragment()
    }

    private fun addStocksListFragment() {
        supportFragmentManager.findFragmentByTag(StockListFragment.TAG) ?: supportFragmentManager
            .beginTransaction()
            .add(R.id.container_fragment, StockListFragment.newInstance(), StockListFragment.TAG)
            .commitAllowingStateLoss()
    }
}
