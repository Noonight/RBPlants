package com.arkhipov.ayur.rbplants.ui.main

import com.arkhipov.ayur.rbplants.base.mvp.MvpView

interface MainView : MvpView
{
    /**
     * Show [SearchFragment]
     * */
    fun showSearch()
}
