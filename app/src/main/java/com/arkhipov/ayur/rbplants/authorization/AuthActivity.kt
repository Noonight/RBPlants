package com.arkhipov.ayur.rbplants.authorization

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.authorization.sign_in.SignInFragment
import com.arkhipov.ayur.rbplants.base.Log
import com.arkhipov.ayur.rbplants.main.MainActivity
import javax.inject.Inject

import com.arkhipov.ayur.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder

class AuthActivity : AppCompatActivity(), AuthView
{
    @Inject
    lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        App[this].component.inject(this)
        presenter.attachView(this)

        NavigationDefaultsHolder.navigationDefaults()
            .navigationIconListener {
                onBackPressed()
            }

        initViews()
    }

    fun initViews()
    {
        /*
        * Скрывает строку состояния
        * */
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
/*
        val toolbar = findViewById<Toolbar>(R.id.toolbar_auth)
        setSupportActionBar(toolbar)

        supportActionBar*/

        //FirebaseAuth.getInstance().signOut()

        /*if (presenter.getCurrentUser() != null)
            Toast.makeText(this, "Current user is here!! welcome back", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "FUUUUUUUUUUUUUUUUUU", Toast.LENGTH_LONG).show()
*/
    }

    override fun onStart()
    {
        super.onStart()
        checkAuthUser()
    }

    override fun checkAuthUser()
    {
        Log.d("Check auth user")
        if (presenter.getCurrentUser() != null)
        {
            Log.d("User found: ${presenter.getCurrentUser()!!.email}")
            showMain()
        } else
        {
            Log.d("Not found authorized user!")
            showSignIn()
        }
    }

    override fun showSignIn()
    {
        Log.d("Show SignInFragment")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.auth_fragment_container, SignInFragment())
            .commit()
    }

    override fun showSteppersGuide()
    {
        Log.d("Show steppers guide")
    }

    override fun showMain()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Log.d("Start MainActivity")
        finish()
        Log.d("Finish AuthActivity")
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        Log.d("Navigation item BACK pressed!")
    }
}