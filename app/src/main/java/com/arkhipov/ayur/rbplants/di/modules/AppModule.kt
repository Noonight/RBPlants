package com.arkhipov.ayur.rbplants.di.modules

import android.content.Context
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.data.AppRealmModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * Все методы классов в одном
 * {@link ContextModule}, [ContextModule]
 * {@link FirebaseModule}, [FirebaseModule]
 * {@link RealmHelperModule} [RealmHelperModule]
 */
@Module
class AppModule(private val app: App) {
    @Singleton
    @Provides
    fun provideAppContext(): Context = app

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestorage() = FirebaseStorage.getInstance()

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