package com.arkhipov.ayur.rbplants.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.arkhipov.ayur.rbplants.base.fragmentnavigations.NavigationDefaults
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.ui.main.search.SearchFragment
import com.arkhipov.ayur.rbplants.utils.Log
import com.arkhipov.ayur.rbplants.utils.DialogUtils
import com.arkhipov.ayur.rbplants.utils.SnackbarUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView
{
    @Inject
    lateinit var presenter: MainPresenter

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

        initViews()
    }

    private fun initViews()
    {
        showSearch()

    }

    override fun showSearch()
    {
        Log.d("Show search fragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, SearchFragment(), SearchFragment::class.java.simpleName)
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