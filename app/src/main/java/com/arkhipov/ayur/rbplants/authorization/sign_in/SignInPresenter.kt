package com.arkhipov.ayur.rbplants.authorization.sign_in

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.arkhipov.ayur.rbplants.authorization.AuthActivity
import com.arkhipov.ayur.rbplants.base.Log
import com.arkhipov.ayur.rbplants.base.mvp.MvpPresenter
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SignInPresenter @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : MvpPresenter<SignInView>()
{


    override fun updateView()
    {

    }

    fun signInEmailPassword(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    Log.d(firebaseAuth.currentUser!!.email)
                } else
                {
                    Log.w(it.exception)
                }
            }
        /*firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as AppCompatActivity, object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>)
                {
                    Log.d(firebaseAuth.currentUser!!.email)
                    view.showToast("Hello")
                }

            })*/
    }
}
