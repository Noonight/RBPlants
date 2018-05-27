package com.arkhipov.ayur.rbplants.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.base.fragmentnavigations.NavigationDefaults
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.DATA
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.PROFILE_MENU
import com.arkhipov.ayur.rbplants.ui.NavigationIds.Companion.BottomItems.Companion.SEARCH
import com.arkhipov.ayur.rbplants.ui.main.data.DataFragment
import com.arkhipov.ayur.rbplants.ui.main.menu.MenuProfileFragment
import com.arkhipov.ayur.rbplants.ui.main.search.SearchFragment
import com.arkhipov.ayur.rbplants.utils.DialogUtils
import com.arkhipov.ayur.rbplants.utils.Log
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView
{
    @Inject
    lateinit var presenter: MainPresenter
    private lateinit var bottomNavigation: AHBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App[this].component.inject(this)
        presenter.attachView(this)

        NavigationDefaults.NavigationDefaultsHolder.navigationDefaults()
            .navigationIconListener {
                onBackPressed()
            }

        bottomNavigation()

        initViews()
    }

    private fun initViews()
    {
        showSearch()

    }

    @SuppressLint("ResourceAsColor")
    fun bottomNavigation()
    {
        bottomNavigation = findViewById(R.id.main_bottom_navigation)
        bottomNavigation.addItems(
            NavigationDefaults.NavigationDefaultsHolder
                .navigationDefaults()
                .navigationItems()
                .bottomNavigationItems())
        bottomNavigation.accentColor = resources.getColor(R.color.accent)
        bottomNavigation.setOnTabSelectedListener(object : AHBottomNavigation.OnTabSelectedListener
        {
            override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean
            {
                Log.d(" Bottom navigation pressed: position = $position and wasSelected = $wasSelected")
                when (position)
                {
                /**
                 * [SEARCH]
                 * */
                    0 -> if (!wasSelected) showSearch()
                /**
                 * [DATA]
                 * */
                    1 -> if (!wasSelected) showData()
                /**
                 * [PROFILE_MENU]
                 * */
                    2 -> if (!wasSelected) showProfileMenu()
                }
                return true
            }

        })
    }

    override fun showSearch()
    {
        Log.d("Show search fragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, SearchFragment(), SearchFragment::class.java.simpleName)
            .commit()
    }

    override fun showData()
    {
        Log.d("Show data fragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, DataFragment())
            .commit()
    }

    override fun showProfileMenu()
    {
        Log.d("Show menu profile fragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MenuProfileFragment())
            .commit()
    }

    override fun onBackPressed()
    {
        Log.d("Back stack entry count = ${supportFragmentManager.backStackEntryCount}")
        if (isBackStackEmpty())
        {
            DialogUtils.createOkCancel(this, R.string.alert, R.string.confirm_exit, ok = {
                Log.d("Exit DialogUtils positive pressed")
                finish()
            }, cancel = {
                Log.d("Exit Dialog Utils negative pressed")
            }).show()
        } else
        {
            super.onBackPressed()
            Log.d("Navigation item BACK pressed!")
        }
    }

    fun isBackStackEmpty(): Boolean
    {
        if (supportFragmentManager.backStackEntryCount == 0)
        {
            return true
        }
        return false
    }
}