package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface SignUpView : MvpView {
    fun showMain()
    fun showSignIn()

    fun onSignUpBtnPressed()
    fun onSignInTvPressed()
}
