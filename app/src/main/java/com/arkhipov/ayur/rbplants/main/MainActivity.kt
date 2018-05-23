package com.arkhipov.ayur.rbplants.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationDefaults
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.base.Log
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


    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        Log.d("Navigation item BACK pressed!")
    }
}