package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView

interface SignUpView : MvpView {
    fun showMain()
    fun showSignIn()

    fun onSignUpBtnPressed()
    fun onSignInTvPressed()

    fun showInvalidEmail()
    fun showInvalidPassword()
    fun showInvalidConfirmPassword()
    fun showInvalidFullName()

    fun hideInvalidEmail()
    fun hideInvalidPassword()
    fun hideInvalidConfirmPassword()
    fun hideInvalidFullName()

    fun onEtFullNameTextChanged()
    fun onEtEmailTextChanged()
    fun onEtPasswordTextChanged()
    fun onEtConfirmPasswordTextChanged()

    fun showToast(message: String)
}
