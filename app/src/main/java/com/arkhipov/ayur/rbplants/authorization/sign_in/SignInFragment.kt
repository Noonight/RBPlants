package com.arkhipov.ayur.rbplants.authorization.sign_in

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.authorization.sign_up.SignUpFragment
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : NavigationFragment(), SignInView
{
    @Inject
    lateinit var presenter: SignInPresenter

    companion object
    {
        fun newInstance(bundle: Bundle): SignInFragment
        {
            val mInstance = SignInFragment()
            mInstance.arguments = bundle
            return mInstance
        }
    }

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>>
    {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_sign_in)
            .includeToolbar()
            .toolbarTitleRes(R.string.sign_in)
            .toolbarNavigationIcon(-1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        //val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
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

    @SuppressLint("ResourceAsColor")
    fun initViews()
    {
        btn_sign_in_signin.setOnClickListener {
            presenter.signInEmailPassword(et_email_signin.text.toString(), et_password_signin.text.toString())
        }

        btn_sign_up_signin.setOnClickListener {
            showSignUp()
        }
    }

    override fun showSignUp()
    {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_fragment_container, SignUpFragment(), SignUpFragment::javaClass.name)
            .addToBackStack(null)
            .commit()
    }

}
