package com.arkhipov.ayur.rbplants.ui.main.profile

import android.graphics.drawable.Drawable
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter
import com.arkhipov.ayur.rbplants.any.utils.Log
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

    fun getAvatar(): Drawable? {
        /*firebaseAuth.currentUser?.photoUrl*/
        return null
    }

    fun getFullName(): CharSequence? {
        var fullName = ""
        firestore.collection("users")
            .get()
            .addOnCompleteListener {
                if (it.isComplete) {
                    it.result.documents.forEach {
                        if (it.data.containsKey("full_name")) {
                            Log.d("Full name found!!! ${it.data.keys}")
                        }
                        fullName = it.data.toString()
                        Log.d(it.data.toString())
                    }
                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }
        return fullName
    }

    fun getEmail(): CharSequence? {
        return null
    }

    fun getScore(): CharSequence? {
        return null
    }
}