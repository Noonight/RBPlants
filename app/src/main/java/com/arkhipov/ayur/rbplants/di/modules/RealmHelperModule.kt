package com.arkhipov.ayur.rbplants.di.modules

import com.arkhipov.ayur.rbplants.data.AppRealmModule
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class RealmHelperModule {
    @Provides
    @Singleton
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm = Realm.getInstance(realmConfiguration)

    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration = RealmConfiguration.Builder()
        .modules(AppRealmModule())
        .name("rbplants.realm")
        .schemaVersion(1)
        .build()
}