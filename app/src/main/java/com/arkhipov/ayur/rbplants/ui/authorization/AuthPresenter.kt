package com.arkhipov.ayur.rbplants.ui.authorization

import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class AuthPresenter @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : MvpPresenter<AuthView>() {

    fun getCurrentUser(): FirebaseUser? {
        if (firebaseAuth.currentUser != null)
            return firebaseAuth.currentUser
        return null
    }
}
