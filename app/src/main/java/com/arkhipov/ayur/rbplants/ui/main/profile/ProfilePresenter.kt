package com.arkhipov.ayur.rbplants.ui.main.profile

import android.graphics.drawable.Drawable
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) : MvpPresenter<ProfileView>() {
    
    fun signOut() {
        Log.d("Sign out!!")
        firebaseAuth.signOut()
        view.showAuthActivity()
    }

    fun onInitView() {
        val user = firebaseAuth.currentUser
        /*firestore.collection("users")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val document = it.result.filter { it.id == user!!.uid }.getOrNull(0)
                    val getUser = document.let { document?.data }
                    val user = UserFire.importDoc(getUser!!)
                    view.bindTvFullName(user.fullName)
                    view.bindTvEmail(user.email)
                    view.bindTvScore(user.score.toString())
                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }*/
    }
}