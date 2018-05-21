package com.arkhipov.ayur.rbplants.authorization

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import javax.inject.Inject

class AuthActivity : AppCompatActivity(), AuthView {

    @Inject
    lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        App[this].component.inject(this)

        initViews()
    }

    fun initViews() {
        /*
        * Скрывает строку состояния
        * */
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        presenter.attachView(this)

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

}