package com.arkhipov.ayur.rbplants.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
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

        initViews()
    }

    private fun initViews()
    {


    }
}