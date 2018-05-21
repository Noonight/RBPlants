package com.arkhipov.ayur.rbplants.di.components

import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.authorization.AuthPresenter
import com.arkhipov.ayur.rbplants.di.modules.AppModule
import com.arkhipov.ayur.rbplants.di.modules.ContextModule
import com.arkhipov.ayur.rbplants.di.modules.FirebaseModule
import com.arkhipov.ayur.rbplants.di.modules.RealmHelperModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(FirebaseModule::class), (ContextModule::class), (RealmHelperModule::class)])
interface AppComponent
{
    fun inject(app: App)

    fun inject(authActivity: AuthActivity)
}

@Singleton
@Component(modules = [AppModule::class])
interface AuthComponent
{
    fun inject(authActivity: AuthActivity)

    fun presenter(): AuthPresenter
}