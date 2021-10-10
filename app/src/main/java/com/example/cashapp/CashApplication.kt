package com.example.cashapp

import android.app.Application
import com.example.cashapp.di.components.ApplicationComponent
import com.example.cashapp.di.components.DaggerApplicationComponent
import com.example.cashapp.di.modules.ApplicationModule

class CashApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}
