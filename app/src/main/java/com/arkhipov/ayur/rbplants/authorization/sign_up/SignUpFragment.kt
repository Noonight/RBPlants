package com.arkhipov.ayur.rbplants.authorization.sign_up

import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpFragment

class SignUpFragment : MvpFragment<SignUpView, SignUpPresenter>(), SignUpView {

    override fun injectDependencies()
    {

    }

    override fun createPresenter(): SignUpPresenter {
        return SignUpPresenter()
    }

}