package com.arkhipov.ayur.rbplants.base

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseMvpView : MvpView
{
    /**
     * Необходимо вызывать в onCreate() до super.***
     * */
    abstract fun injectDependencies()
}