package com.arkhipov.ayur.rbplants.authorization.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.R
import com.hannesdorfmann.mosby3.mvp.MvpFragment

class SignInFragment : MvpFragment<SignInView, SignInPresenter>(), SignInView {



    override fun injectDependencies()
    {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {

    }

    override fun createPresenter(): SignInPresenter {
        return SignInPresenter()
    }

}
