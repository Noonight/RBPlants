package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import android.text.Editable
import android.widget.Toast
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.InputFieldUtils
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : MvpPresenter<SignUpView>() {

    fun onSignUpButtonPressed(fullName: Editable, email: Editable, password: Editable, confirmPassword: Editable) {
        if (isInputFieldsCorrect(
                fullName,
                email,
                password,
                confirmPassword)) {
            signUp(fullName.toString(), email.toString(), password.toString())
        } else {

        }
    }

    private fun isInputFieldsCorrect(fullName: Editable, email: Editable, password: Editable, confirmPassword: Editable): Boolean {
        if (!InputFieldUtils.isEmpty(fullName)) {
            if (!InputFieldUtils.isEmpty(email, password, confirmPassword)) {
                if (InputFieldUtils.isEmailValid(email.toString())) {
                    if (InputFieldUtils.isPasswordValid(password.toString())) {
                        if (password.toString() == confirmPassword.toString()) {
                            return true
                        } else {
                            view.showInvalidConfirmPassword()
                        }
                    } else {
                        view.showInvalidPassword()
                    }
                } else {
                    view.showInvalidEmail()
                }
            } else {
                view.showToast("Fields can't be empty")
            }
        } else {
            view.showInvalidFullName()
        }
        return false
    }

    fun onSignInTvPressed() {
        view.showSignIn()
    }

    private fun signUp(fullName: String, email: String, password: String) {
        fireAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Registration complete -> $it")
                    signIn(email, password)
                    fireStoreSave(fullName)
                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }
    }

    private fun fireStoreSave(fullName: String) {
        val curUser = fireAuth.currentUser
        val user = UserFire(curUser?.email!!, fullName, role = UserFire.TYPE_USER)
        fireStore.collection("users")
            .document(fireAuth.currentUser?.uid!!)
            .set(user)
            .addOnCompleteListener {
                Log.d("Complete save user with uid ${curUser.uid}")
            }
            .addOnFailureListener {
                Log.d("Error $it")
            }
    }

    private fun signIn(email: String, password: String) {
        fireAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.showMain()
                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }
    }
}