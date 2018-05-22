package com.arkhipov.ayur.rbplants.authorization.sign_in

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.di.modules.AppModule
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

    fun initViews() {

    }

}
