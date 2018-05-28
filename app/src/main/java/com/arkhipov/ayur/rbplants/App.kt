package com.arkhipov.ayur.rbplants

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.arkhipov.ayur.rbplants.base.fragmentnavigations.NavigationDefaults
import com.arkhipov.ayur.rbplants.di.AppComponent
import com.arkhipov.ayur.rbplants.di.DaggerAppComponent
import com.arkhipov.ayur.rbplants.di.modules.ContextModule
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.realm.Realm
import javax.inject.Inject

import com.arkhipov.ayur.rbplants.base.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.DATA
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.PROFILE_MENU
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.SEARCH
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.MenuItems.Companion.BACK
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.MenuItems.Companion.NAV_MENU

class App : Application()
{

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var firestore: FirebaseFirestore

    lateinit var component: AppComponent

    companion object
    {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App

        operator fun get(context: Context) = context.applicationContext as App
    }

    override fun onCreate()
    {
        instance = this
        super.onCreate()

        component = createDaggerComponents()
        component.inject(this)

        //FirebaseApp.initializeApp(context)
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false) // true
            .build()

        Realm.init(this)

        initNavigation()
    }

    private fun createDaggerComponents() = DaggerAppComponent.builder()
        .contextModule(ContextModule(this))
        .build()

    /**
     * Initailization librari config for navigation menu
     * */
    private fun initNavigation()
    {
        NavigationDefaultsHolder.initDefaults(NavigationDefaults()
            .navigationIcon(BACK, R.drawable.arrow_back)
            .navigationIcon(NAV_MENU, R.drawable.more_vert)
            .navigationItem(SEARCH, R.string.nav_item_search, R.drawable.search, R.color.accent)
            .navigationItem(DATA, R.string.nav_item_data, R.drawable.like_flower, R.color.primary)
            .navigationItem(PROFILE_MENU, R.string.nav_item_profile_menu, R.drawable.profile, R.color.secondary_text)
            .defaultBottomNavigationItem(SEARCH)
            .defaultNavigationIconType(BACK))
    }
}