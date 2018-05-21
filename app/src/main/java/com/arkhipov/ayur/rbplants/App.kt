package com.arkhipov.ayur.rbplants

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.arkhipov.ayur.rbplants.di.AppComponent
import com.arkhipov.ayur.rbplants.di.DaggerAppComponent
import com.arkhipov.ayur.rbplants.di.modules.ContextModule
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.realm.Realm
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var firestore: FirebaseFirestore

    lateinit var component: AppComponent

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App

        operator fun get(context: Context) = context.applicationContext as App
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

        component = createDaggerComponents()
        component.inject(this)

        //FirebaseApp.initializeApp(context)
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build()

        Realm.init(this)

    }

    private fun createDaggerComponents() = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
}