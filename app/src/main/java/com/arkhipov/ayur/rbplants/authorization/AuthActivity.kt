package com.arkhipov.ayur.rbplants.authorization

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder
import com.arkhipov.ayur.rbplants.App
import com.arkhipov.ayur.rbplants.R
import com.arkhipov.ayur.rbplants.authorization.sign_in.SignInFragment
import com.arkhipov.ayur.rbplants.base.Log
import com.arkhipov.ayur.rbplants.main.MainActivity
import com.arkhipov.ayur.rbplants.utils.DialogUtils
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthActivity : AppCompatActivity(), AuthView
{
    @Inject
    lateinit var presenter: AuthPresenter
    //@Inject
    lateinit var dialog: DialogUtils

    override fun onCreate(savedInstanceState: Bundle?)
    {
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

    fun initViews()
    {
        FirebaseAuth.getInstance().signOut()
    }

    fun initDialog()
    {
        dialog = DialogUtils(this)
        /*dialog.positivePressed =  object : DialogUtils.OnPositivePressed
        {
            override fun onPress()
            {
                Log.d("Exit DialogUtils positive pressed")
                finish()
            }
        }*/
        dialog.setPositivePressedListener(action = object : DialogUtils.OnPositivePressed
        {
            override fun onPress()
            {
                Log.d("Exit DialogUtils positive pressed")
                finish()
            }

        })
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
            .replace(R.id.auth_fragment_container, SignInFragment(), SignInFragment::javaClass.name)
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
        Log.d("Back stack entry count = ${supportFragmentManager.backStackEntryCount}")
        if (isBackStackEmpty())
        {
            dialog.createAlertDialog().show()
        } else
        {
            super.onBackPressed()
            Log.d("Navigation item BACK pressed!")
        }
    }

    fun isBackStackEmpty(): Boolean {
        if (supportFragmentManager.backStackEntryCount == 0)
        {
            return true
        }
        return false
    }
}

//Скрывает строку состояния
//window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
