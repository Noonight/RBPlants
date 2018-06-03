package com.arkhipov.ayur.rbplants.ui.authorization.sign_in

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTextChanged
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AutoLayoutNavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationBuilder
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.any.utils.DialogUtils
import com.arkhipov.ayur.rbplants.any.utils.InputFieldUtils
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.any.utils.SnackbarUtils
import com.arkhipov.ayur.rbplants.ui.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.ui.authorization.sign_up.SignUpFragment
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : NavigationFragment(), SignInView {
    @Inject
    lateinit var presenter: SignInPresenter

    override fun buildNavigation(): NavigationBuilder<out NavigationBuilder<*>> {
        return AutoLayoutNavigationBuilder.navigation(R.layout.fragment_sign_in)
            .includeToolbar()
            .toolbarTitleRes(R.string.sign_in)
            .toolbarNavigationIcon(-1)
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

    @SuppressLint("ResourceAsColor", "ResourceType")
    fun initViews() {
    }

    override fun showSignUp() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_fragment_container, SignUpFragment(), SignUpFragment::javaClass.name)
            .addToBackStack(null)
            .commit()
    }

    override fun showMain() {
        (activity!! as AuthActivity).showMain()
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showEmailSendSnack(email: String) {
        SnackbarUtils
            .create(view!!, "${resources.getString(R.string.email_sended)} on $email")
            .show()
    }

    override fun showEmailNotSendSnack(email: String) {
        SnackbarUtils
            .create(view!!, "${resources.getString(R.string.email_not_sended)} on $email")
            .show()
    }

    override fun showInvalidEmail() {
        ti_wrapper_et_email_signin.error = resources.getString(R.string.invalid_email_address)
    }

    override fun showInvalidPassword() {
        ti_wrapper_et_password_signin.error = resources.getString(R.string.invalid_password)
    }

    override fun hideInvalidEmail() {
        ti_wrapper_et_email_signin.error = null
    }

    override fun hideInvalidPassword() {
        ti_wrapper_et_password_signin.error = null
    }

    @OnClick(R.id.btn_sign_in_signin)
    override fun onSignInBtnPressed() {
        presenter.onSignInBtnPressed(
            et_email_signin.text.toString(),
            et_password_signin.text.toString())
    }

    @OnClick(R.id.btn_sign_up_signin)
    override fun onSignUpBtnPressed() {
        presenter.onSignUpBtnPressed()
    }

    @OnClick(R.id.tv_recovery_password_signin)
    override fun onRecoverTvPressed() {
        DialogUtils.createEmailOkCancel(context!!, R.string.recovery_password, R.string.input_password, ok = {
            presenter.onRecoveryTvPressed(it)
            Log.d("recovery ok dialog pressed")
        }).show()
    }

    @OnTextChanged(R.id.et_email_signin)
    override fun onEtEmailTextChanged() {
        if (ti_wrapper_et_email_signin.error != null)
            hideInvalidEmail()
    }

    @OnTextChanged(R.id.et_password_signin)
    override fun onEtPasswordTextChanged() {
        if (ti_wrapper_et_password_signin.error != null)
            hideInvalidPassword()
    }
}
