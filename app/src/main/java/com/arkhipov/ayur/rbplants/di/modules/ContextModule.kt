package com.arkhipov.ayur.rbplants.di.modules

import android.content.Context
import com.arkhipov.ayur.rbplants.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val app: App) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = app
}