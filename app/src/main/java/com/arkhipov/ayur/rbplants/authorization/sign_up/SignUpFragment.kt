package com.arkhipov.ayur.rbplants.authorization.sign_up

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationFragment
import javax.inject.Inject

class SignUpFragment : NavigationFragment(), SignUpView {

    @Inject
    lateinit var presenter: SignUpPresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>>
    {
        return return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_sign_up)
            .includeToolbar()
            .toolbarTitleRes(R.string.sign_up)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        //val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        App[activity!!].component.inject(this)
        presenter.attachView(this)
        //return view
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {

    }
}