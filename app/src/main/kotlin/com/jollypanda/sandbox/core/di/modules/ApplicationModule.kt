package com.jollypanda.sandbox.core.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yamushev Igor
 * @since  08.06.18
 */
@Module
class ApplicationModule(private val context: Context) {
    
    @Provides
    @Singleton
    fun provideContext() = context
}