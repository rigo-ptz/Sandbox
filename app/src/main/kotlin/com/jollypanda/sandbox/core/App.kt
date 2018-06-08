package com.jollypanda.sandbox.core

import android.support.multidex.MultiDexApplication
import com.jollypanda.sandbox.core.di.AppComponent
import com.jollypanda.sandbox.core.di.DaggerAppComponent
import com.jollypanda.sandbox.core.di.modules.ApplicationModule
import com.jollypanda.sandbox.core.di.modules.DataModule
import com.jollypanda.sandbox.core.di.modules.RepositoriesModule

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
class App : MultiDexApplication() {
    
    override fun onCreate() {
        super.onCreate()
        initDi()
    }
    
    private fun initDi() {
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataModule(DataModule())
            .repositoriesModule(RepositoriesModule())
            .build()
    }
    
    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}