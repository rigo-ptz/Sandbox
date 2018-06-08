package com.jollypanda.sandbox.core.di

import com.jollypanda.sandbox.core.di.modules.ApplicationModule
import com.jollypanda.sandbox.core.di.modules.DataModule
import com.jollypanda.sandbox.core.di.modules.RepositoriesModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author Yamushev Igor
 * @since  07.06.18
 */
@Singleton
@Component(modules = [RepositoriesModule::class, DataModule::class, ApplicationModule::class])
interface AppComponent {
    fun injectTo(injector: Injector)
}