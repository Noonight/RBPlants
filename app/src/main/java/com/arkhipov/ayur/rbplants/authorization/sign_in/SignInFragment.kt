package com.arkhipov.ayur.rbplants.authorization.sign_in

import com.hannesdorfmann.mosby3.mvp.MvpFragment

class SignInFragment : MvpFragment<SignInView, SignInPresenter>(), SignInView {



    override fun createPresenter(): SignInPresenter {
        return SignInPresenter()
    }

}
