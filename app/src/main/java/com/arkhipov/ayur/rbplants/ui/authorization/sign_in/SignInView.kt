package com.arkhipov.ayur.rbplants.ui.authorization.sign_in

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface SignInView : MvpView {


    fun showSignUp()
    fun showMain()

    fun showInvalidEmail()
    fun showInvalidPassword()
    fun showEmailSendSnack(email: String)

    fun hideInvalidEmail()
    fun hideInvalidPassword()

    fun showToast(message: String)

    fun onSignInBtnPressed()
    fun onSignUpBtnPressed()
    fun onRecoverTvPressed()

    fun onEtEmailTextChanged()
    fun onEtPasswordTextChanged()
}
