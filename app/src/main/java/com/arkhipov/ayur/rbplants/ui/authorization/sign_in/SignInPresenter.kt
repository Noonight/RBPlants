package com.arkhipov.ayur.rbplants.ui.authorization.sign_in

import com.arkhipov.ayur.rbplants.utils.Log
import com.arkhipov.ayur.rbplants.base.mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.utils.InputFieldUtils
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
                    view.showMain()
                    Log.d(firebaseAuth.currentUser!!.email)
                } else
                {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                view.showToast(it.message.toString())
            }
    }

    fun onSignInButtonPressed(email: String, password: String)
    {
        if (!InputFieldUtils.isEmpty(email) && !InputFieldUtils.isEmpty(password))
        {
            signInEmailPassword(email, password)
        } else
        {
            view.showInvalidInputData()
        }
        /*if (!InputFieldUtils.isEmailValid(email) && !InputFieldUtils.isPasswordValid(password))
        {
            view.showInvalidInputData()
            return
        }*/


    }
}
