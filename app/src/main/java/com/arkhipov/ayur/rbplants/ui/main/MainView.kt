package com.arkhipov.ayur.rbplants.ui.main

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface MainView : MvpView
{
    /**
     * Show [SearchFragment]
     * */
    fun showSearch()

    /**
     * Show [DataFragment]
     * */
    fun showData()

    /**
     * Show [ProfileFragment]*/
    fun showProfile()
}
