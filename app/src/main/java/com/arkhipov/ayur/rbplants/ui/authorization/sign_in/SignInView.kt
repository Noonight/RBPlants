package com.arkhipov.ayur.rbplants.ui.authorization.sign_in

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface SignInView : MvpView
{
    /**
     * Show [SignUpFragment]
     * */
    fun showSignUp()

    /**
     * Show [MainActivity]
     * */
    fun showMain()

    /**
     *  Show errors
     * */
    fun showInvalidInputData()

    fun showToast(message: String)
}
