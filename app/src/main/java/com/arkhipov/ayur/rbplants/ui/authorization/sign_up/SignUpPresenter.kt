package com.arkhipov.ayur.rbplants.ui.authorization.sign_up

import android.text.Editable
import android.widget.Toast
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.InputFieldUtils
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.ReposCallback
import com.arkhipov.ayur.rbplants.data.ReposManager
import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val reposManager: ReposManager
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
        val user = UserFire(
            uId = curUser!!.uid,
            email = curUser.email!!,
            fullName = fullName,
            role = UserFire.TYPE_USER
        )
        /*fireStore.collection("users")
            .document(fireAuth.currentUser?.uid!!)
            .set(user)
            .addOnCompleteListener {
                Log.d("Complete save user with uid ${curUser.uid}")
            }
            .addOnFailureListener {
                Log.d("Error $it")
            }*/
        reposManager.addUser(user, object : ReposCallback<UserFire> {
            override fun onSuccess() {
            }

            override fun onSuccess(response: UserFire) {
                view.showToast("$response success create user")
                Log.d("Complete save user with uid ${curUser.uid}")
            }

            override fun onError(e: Exception) {
                view.showToast("${e.message}")
                Log.d("Error $e")
            }
        })
    }

    private fun signIn(email: String, password: String) {
        fireAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    view.showMain()
                } else {
                    when (it.exception) {
                        it as FirebaseAuthWeakPasswordException -> {
                            view.showInvalidPassword()
                        }
                    }
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }
    }
}