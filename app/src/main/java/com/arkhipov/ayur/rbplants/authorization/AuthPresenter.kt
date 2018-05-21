package com.arkhipov.ayur.rbplants.authorization

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject


class AuthPresenter @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : MvpBasePresenter<AuthView>()
{

    fun getCurrentUser(): FirebaseUser?
    {
        if (firebaseAuth.currentUser != null)
            return firebaseAuth.currentUser
        return null
    }
}
