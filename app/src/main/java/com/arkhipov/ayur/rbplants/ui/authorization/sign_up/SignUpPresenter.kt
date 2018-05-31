package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import android.text.Editable
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.InputFieldUtils
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : MvpPresenter<SignUpView>() {



    fun signUp(fullName: String, email: String, password: String) {
        fireAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Registration complete -> $it")
                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }

    }

    private fun signIn(email: String, password: String) {
        fireAuth.signInWithEmailAndPassword(email, password)
    }

    fun onSignUpButtonPressed(fullName: Editable, email: Editable, password: Editable) {
        if (!InputFieldUtils.isEmpty(fullName)) {
            if (!InputFieldUtils.isEmpty(email, password)) {

            }
        }
    }

    fun onSignInTvPressed() {
        view.showSignIn()
    }

}