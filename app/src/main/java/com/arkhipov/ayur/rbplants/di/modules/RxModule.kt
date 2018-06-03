package com.arkhipov.ayur.rbplants.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class RxModule {

    @Provides
    @Singleton
    fun profiveCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}