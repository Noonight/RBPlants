package com.arkhipov.ayur.rbplants.ui.main.profile

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

    override fun updateView() {

    }
}