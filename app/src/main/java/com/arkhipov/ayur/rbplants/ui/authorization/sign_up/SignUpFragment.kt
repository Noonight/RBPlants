package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTextChanged
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
            et_password_signup.text,
            et_confirm_password_signup.text
        )
    }

    @OnClick(R.id.tv_sign_in_signup)
    override fun onSignInTvPressed() {
        presenter.onSignInTvPressed()
    }

    override fun showSignIn() {
        activity!!.onBackPressed()
    }

    override fun showInvalidFullName() {
        ti_wrapper_et_full_name_signup.error = resources.getString(R.string.not_be_empty)
    }

    override fun showInvalidEmail() {
        ti_wrapper_et_email_signup.error = resources.getString(R.string.invalid_email_address)
    }

    override fun showInvalidPassword() {
        ti_wrapper_et_password_signup.error = resources.getString(R.string.invalid_password)
    }

    override fun showInvalidConfirmPassword() {
        ti_wrapper_et_confirm_password_signup.error = resources.getString(R.string.not_match_password)
    }

    override fun hideInvalidFullName() {
        ti_wrapper_et_full_name_signup.error = null
    }

    override fun hideInvalidEmail() {
        ti_wrapper_et_email_signup.error = null
    }

    override fun hideInvalidPassword() {
        ti_wrapper_et_password_signup.error = null
    }

    override fun hideInvalidConfirmPassword() {
        ti_wrapper_et_confirm_password_signup.error = null
    }

    @OnTextChanged(R.id.et_email_signup)
    override fun onEtEmailTextChanged() {
        if (ti_wrapper_et_email_signup != null)
            hideInvalidEmail()
    }

    @OnTextChanged(R.id.et_password_signup)
    override fun onEtPasswordTextChanged() {
        if (ti_wrapper_et_password_signup != null) {
            hideInvalidPassword()
            hideInvalidConfirmPassword()
            clearEtConfirmPassword()
        }
    }

    @OnTextChanged(R.id.et_confirm_password_signup)
    override fun onEtConfirmPasswordTextChanged() {
        if (ti_wrapper_et_password_signup != null)
            hideInvalidConfirmPassword()
    }

    @OnTextChanged(R.id.et_full_name_signup)
    override fun onEtFullNameTextChanged() {
        if (ti_wrapper_et_full_name_signup != null)
            hideInvalidFullName()
    }

    private fun clearEtConfirmPassword() {
        et_confirm_password_signup.text.clear()
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}