package com.arkhipov.ayur.rbplants.authorization.sign_in

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : Fragment(), SignInView {


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        App[activity!!].component.inject(this)
        presenter.attachView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("ResourceAsColor")
    fun initViews() {

        //activity!!.actionBar.customView.setBackgroundColor(R.color.browser_actions_divider_color)

        btn_sign_in_signin.setOnClickListener {
            presenter.signInEmailPassword(et_email_signin.text.toString(), et_password_signin.text.toString())
        }

        btn_sign_up_signin.setOnClickListener {

        }

    }

}
