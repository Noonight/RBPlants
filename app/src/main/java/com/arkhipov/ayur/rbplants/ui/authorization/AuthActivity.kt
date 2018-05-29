package com.arkhipov.ayur.rbplants.ui.authorization

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.ui.authorization.sign_in.SignInFragment
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder
import com.arkhipov.ayur.rbplants.ui.main.MainActivity
import com.arkhipov.ayur.rbplants.any.utils.DialogUtils
import javax.inject.Inject

class AuthActivity : AppCompatActivity(), AuthView {
    @Inject
    lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        App[this].component.inject(this)
        presenter.attachView(this)

        initDialog()

        NavigationDefaultsHolder.navigationDefaults()
            .navigationIconListener {
                onBackPressed()
            }

        initViews()
    }

    fun initViews() {
        //FirebaseAuth.getInstance().signOut()
    }

    fun initDialog() {

    }

    override fun onStart() {
        super.onStart()
        checkAuthUser()
    }

    override fun checkAuthUser() {
        Log.d("Check auth user")
        if (presenter.getCurrentUser() != null) {
            Log.d("User found: ${presenter.getCurrentUser()!!.email}")
            showMain()
        } else {
            Log.d("Not found authorized user!")
            showSignIn()
        }
    }

    override fun showSignIn() {
        Log.d("Show SignInFragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_fragment_container, SignInFragment(), SignInFragment::javaClass.name)
            .commit()
    }

    override fun showSteppersGuide() {
        Log.d("Show steppers guide")
    }

    override fun showMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Log.d("Start MainActivity")
        finish()
        Log.d("Finish AuthActivity")
    }

    override fun onBackPressed() {
        Log.d("Back stack entry count = ${supportFragmentManager.backStackEntryCount}")
        if (isBackStackEmpty()) {
            DialogUtils.createOkCancel(this, R.string.alert, R.string.confirm_exit, ok = {
                Log.d("Exit DialogUtils positive pressed")
                finish()
            }, cancel = {
                Log.d("Exit Dialog Utils negative pressed")
            }).show()
        } else {
            super.onBackPressed()
            Log.d("Navigation item BACK pressed!")
        }
    }

    fun isBackStackEmpty(): Boolean {
        if (supportFragmentManager.backStackEntryCount == 0) {
            return true
        }
        return false
    }
}

//Скрывает строку состояния
//window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
