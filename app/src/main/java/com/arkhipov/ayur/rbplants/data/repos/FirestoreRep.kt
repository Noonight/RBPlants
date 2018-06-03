package com.arkhipov.ayur.rbplants.data.repos

import com.arkhipov.ayur.rbplants.any.utils.Log
import com.arkhipov.ayur.rbplants.data.model.UserFire
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreRep @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun createUser(userFire: UserFire) {
        firestore.document(userFire.getDocument())
            .set(userFire)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                } else {
                    Log.w(it.exception)
                }
            }
            .addOnFailureListener {
                Log.w(it)
            }
    }
}