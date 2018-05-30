package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.ui.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.any.utils.InputFieldUtils
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

class SignUpFragment : NavigationFragment(), SignUpView {

    @Inject
    lateinit var presenter: SignUpPresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_sign_up)
            .includeToolbar()
            .toolbarTitleRes(R.string.sign_up)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App[activity!!].component.inject(this)
        presenter.attachView(this)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        ButterKnife.bind(this, view!!)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {

    }

    override fun showMain() {
        (activity!! as AuthActivity).showMain()
    }

    @OnClick(R.id.btn_sign_up_signup)
    override fun onSignUpBtnPressed() {
        presenter.onSignUpButtonPressed(
            et_full_name_signup.text,
            et_email_signup.text,
            et_password_signup.text
        )
    }

    @OnClick(R.id.tv_sign_in_signup)
    override fun onSignInTvPressed() {
        presenter.onSignInTvPressed()
    }

    override fun showSignIn() {
        activity!!.onBackPressed()
    }
}