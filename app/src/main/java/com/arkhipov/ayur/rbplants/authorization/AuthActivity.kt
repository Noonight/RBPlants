package com.arkhipov.ayur.rbplants.authorization

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.di.components.AppComponent
import com.arkhipov.ayur.rbplants.di.components.AuthComponent
import com.arkhipov.ayur.rbplants.di.components.DaggerAuthComponent
import com.arkhipov.ayur.rbplants.di.modules.AppModule
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import javax.inject.Inject

class AuthActivity : MvpActivity<AuthView, AuthPresenter>(), AuthView {

    protected var authComponent: AuthComponent? = null

    override fun injectDependencies() {
        authComponent = DaggerAuthComponent.builder().appModule(AppModule(App.get(this))).build()
        authComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        initViews()
    }

    fun initViews() {
        /*
        * Скрывает строку состояния
        * */
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (presenter.getCurrentUser() != null)
            Toast.makeText(this, "Current user is here!! welcome back", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "FUUUUUUUUUUUUUUUUUU", Toast.LENGTH_LONG).show()

    }

    override fun onStart() {
        super.onStart()

        /*val currentUser = mAuth.getCurrentUser()
        updateUI(currentUser)*/
    }


    override fun createPresenter(): AuthPresenter {
        return authComponent!!.presenter()
    }
}