package com.arkhipov.ayur.rbplants.ui.authorization.sign_in

import android.text.TextUtils
import android.widget.Toast
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.InputFieldUtils
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SignInPresenter @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : MvpPresenter<SignInView>() {

    fun onSignInBtnPressed(email: String, password: String) {
        if (!InputFieldUtils.isEmpty(email) && !InputFieldUtils.isEmpty(password)) {
            if (InputFieldUtils.isEmailValid(email)) {
                //view.hideInvalidEmail()
                if (InputFieldUtils.isPasswordValid(password)) {
                    //view.hideInvalidPassword()
                    signIn(email, password)
                } else {
                    view.showInvalidPassword()
                }
            } else {
                view.showInvalidEmail()
            }
        } else {
            view.showToast("Empty fields!")
        }
    }

    fun onSignUpBtnPressed() {
        view.showSignUp()
    }

    fun onRecoveryTvPressed(email: String) {
        if (!TextUtils.isEmpty(email)) {
            recoveryPassword(email)
        } else {
            view.showToast("email is invalid?? what $email")
        }
    }

    private fun recoveryPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.showEmailSendSnack(email)
                } else {
                    view.showEmailNotSendSnack(email)
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }
    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.showMain()
                    Log.d(firebaseAuth.currentUser.toString())
                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                view.showToast(it.message.toString())
            }
    }
}
